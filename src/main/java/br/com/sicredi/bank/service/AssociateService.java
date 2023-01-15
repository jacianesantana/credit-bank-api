package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.AssociateResponse;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.exception.*;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateService {

    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;
    private final AccountService accountService;
    private final ContractService contractService;

    public AssociateResponse save(SaveAssociateRequest saveAssociateRequest) {
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

                    return associateMapper.associateWithAccountsToResponse(associateEntity, accounts);
                } catch (Exception e) {
                    log.error("Não foi possivel salvar o associdado. Motivo: {}", e.getMessage());
                    throw new SaveEntityException("Não foi possível salvar o associado.");
                }
            }
            throw new BusinessRulesException("Idade não atende o mínimo necessário!");

        }

        throw new BusinessRulesException("Não foi possível salvar. Associado já existe!");
    }

    public AssociateResponse findById(Long id) {
        log.info("Buscando associado com o id: {}", id);
        try {
            var associateEntity = associateRepository.findById(id).orElseThrow();
            return associateMapper.associateToAssociateResponse(associateEntity);
        } catch (Exception e) {
            log.error("Associdado não encontrado com o id: {}", id);
            throw new FindEntityException("Associdado não encontrado.");
        }
    }

    public AssociateResponse update(Long id, UpdateAssociateRequest request) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.associateResponseToAssociate(associateResponse);

        log.info("Verificando data da ultima atualização para o id {}", id);
        if (associateEntity.getLastPaycheck().isBefore(LocalDate.now().minusMonths(3))) {
            log.info("Tentando atualizar o associado com o id {}", id);
            try {
                associateEntity.setProfession(request.getProfession());
                associateEntity.setSalary(request.getSalary());
                associateEntity.setLastPaycheck(LocalDate.now());
                associateRepository.save(associateEntity);

                log.info("Associado atualizado com sucesso para o id {}", id);
                return associateMapper.associateToAssociateResponse(associateEntity);
            } catch (Exception e) {
                log.error("Não foi possivel atualizar o associdado. Motivo: {}", e.getMessage());
                throw new UpdateEntityException("Não foi possível atualizar o associado.");
            }
        }

        log.info("Associado com o id {} não pode ser atualizado, menos de três meses desde a ultima atualização: {}",
                associateEntity.getId(), associateEntity.getLastPaycheck());
        throw new BusinessRulesException("Associado com menos de 3 meses desde da última atualização!");
    }

    public void delete(Long id) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.associateResponseToAssociate(associateResponse);
        var contracts = contractService.findContracts(associateEntity);

        if (contracts.isEmpty()) {
            log.info("Tentando excluir o associado com id: {}", associateEntity.getId());
            try {
                associateRepository.deleteById(id);
                log.info("Associado excluído com sucesso!");
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
