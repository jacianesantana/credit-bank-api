package br.com.sicredi.bank.service.associate;

import br.com.sicredi.bank.exception.*;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.model.enums.AccountType;
import br.com.sicredi.bank.model.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.model.request.associate.UpdateAssociateContactRequest;
import br.com.sicredi.bank.model.request.associate.UpdateAssociatePaycheckRequest;
import br.com.sicredi.bank.model.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.model.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.model.response.associate.UpdateAssociateContactResponse;
import br.com.sicredi.bank.model.response.associate.UpdateAssociatePaycheckResponse;
import br.com.sicredi.bank.repository.AssociateRepository;
import br.com.sicredi.bank.service.account.AccountService;
import br.com.sicredi.bank.service.contract.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static br.com.sicredi.bank.model.Message.ASSOCIATE_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateService {

    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;
    private final AccountService accountService;
    private final ContractService contractService;

    public ResponseEntity<SaveAssociateResponse> save(SaveAssociateRequest saveAssociateRequest) {
        var associate = associateRepository.findByCpf(saveAssociateRequest.getCpf());

        if (associate.isEmpty()) {

            if (saveAssociateRequest.getBirthDate().isEqual(LocalDate.now().minusYears(18)) ||
                    saveAssociateRequest.getBirthDate().isBefore(LocalDate.now().minusYears(18))) {
                log.info("Tentando salvar o associado com cpf {}", saveAssociateRequest.getCpf().substring(0, 4).concat("..."));
                try {
                    var associateEntity = associateMapper.saveRequestToAssociate(saveAssociateRequest);
                    associateRepository.save(associateEntity);

                    log.info("Associado salvo com sucesso. Id gerado: {}", associateEntity.getId());

                    var checkingAccount = accountService.create(associateEntity, AccountType.CORRENTE);
                    var savesAccount = accountService.create(associateEntity, AccountType.POUPANCA);
                    var accounts = List.of(checkingAccount, savesAccount);

                    var response = associateMapper.associateToSaveAssociateResponse(associateEntity, accounts);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                } catch (Exception e) {
                    log.error("Não foi possivel salvar o associdado. Motivo: {}", e.getMessage());
                    throw new SaveEntityException("Não foi possível salvar o associado.");
                }
            }
            throw new BusinessRulesException("Idade não atende o mínimo necessário!");

        }

        throw new BusinessRulesException("Não foi possível salvar. Associado já existe!");
    }

    public ResponseEntity<FindAssociateResponse> findById(Long id) {
        log.info("Buscando associado com o id: {}", id);
        try {
            var associateEntity = associateRepository.findById(id).orElseThrow();
            var response = associateMapper.associateToFindAssociateResponse(associateEntity);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Associdado não encontrado com o id: {}", id);
            throw new FindEntityException(ASSOCIATE_ERROR);
        }
    }

    public ResponseEntity<UpdateAssociateContactResponse> updateContact(Long id, UpdateAssociateContactRequest request) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        try {
            associateEntity.setPhone(request.getPhone());
            associateEntity.setEmail(request.getEmail());
            associateRepository.save(associateEntity);

            log.info("Associado atualizado com sucesso para o id {}", id);
            var response = associateMapper.associateToUpdateAssociateContactResponse(associateEntity);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Não foi possivel atualizar o associdado. Motivo: {}", e.getMessage());
            throw new UpdateEntityException("Não foi possível atualizar o associado.");
        }
    }

    public ResponseEntity<UpdateAssociatePaycheckResponse> updatePaycheck(Long id, UpdateAssociatePaycheckRequest request) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        log.info("Verificando data da ultima atualização para o id {}", id);
        if (associateEntity.getLastPaycheck().isBefore(LocalDate.now().minusMonths(3))) {
            log.info("Tentando atualizar o associado com o id {}", id);
            try {
                associateEntity.setProfession(request.getProfession());
                associateEntity.setSalary(request.getSalary());
                associateEntity.setLastPaycheck(LocalDate.now());
                associateRepository.save(associateEntity);

                log.info("Associado atualizado com sucesso para o id {}", id);
                var response = associateMapper.associateToUpdateAssociatePaycheckResponse(associateEntity);

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                log.error("Não foi possivel atualizar o associdado. Motivo: {}", e.getMessage());
                throw new UpdateEntityException("Não foi possível atualizar o associado.");
            }
        }

        log.info("Associado com o id {} não pode ser atualizado, menos de três meses desde a ultima atualização: {}",
                associateEntity.getId(), associateEntity.getLastPaycheck());
        throw new BusinessRulesException("Associado com menos de 3 meses desde da última atualização!");
    }

    public ResponseEntity<Void> delete(Long id) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());
        var contracts = contractService.findContracts(associateEntity);

        if (contracts.isEmpty()) {
            log.info("Tentando excluir o associado com id: {}", associateEntity.getId());
            try {
                associateRepository.deleteById(id);
                log.info("Associado excluído com sucesso!");

                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                log.error("Não foi possível deletar o associdado. Motivo: {}", e.getMessage());
                throw new DeleteEntityException("Não foi possível deletar o associado.");
            }
        } else {
            log.info("Associado não pode ser excluído, pois contém contratos ativos!");
            throw new BusinessRulesException("Associado contém contratos ativos!");
        }
    }

}
