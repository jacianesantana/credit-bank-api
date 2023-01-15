package br.com.sicredi.bank.service;

import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.DeleteAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociateResponse;
import br.com.sicredi.bank.exception.*;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.entity.enums.AccountType;
import br.com.sicredi.bank.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateService {

    private static final String UPDATE_SUCCESS = "Associado atualizado com sucesso!";
    private static final String UPDATE_ERROR = "Associado com menos de 3 meses desde da última atualização!";
    private static final String DELETE_SUCCESS = "Associado excluido com sucesso!";
    private static final String DELETE_ERROR = "Associado contém contratos ativos!";
    private static final BigDecimal MIN_SALARY_ACCEPTABLE = BigDecimal.valueOf(1500);

    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;
    private final ContractService contractService;
    private final AccountService accountService;

    public SaveAssociateResponse save(SaveAssociateRequest request) {
        var associate = associateRepository.findByCpf(request.getCpf());

        if (associate.isEmpty()) {
            validateBusinessRules(request);

            log.info("Tentando salvar o associado com cpf {}", request.getCpf().substring(0, 4).concat("..."));
            try {
                var entity = associateMapper.saveRequestToAssociate(request);
                var savedAssociate = associateRepository.save(entity);

                log.info("Associado salvo com sucesso. Id gerado: {}", savedAssociate.getId());

                var checkingAccount = accountService.create(savedAssociate, AccountType.CORRENTE);
                var savesAccount = accountService.create(savedAssociate, AccountType.POUPANCA);
                var accounts = List.of(checkingAccount, savesAccount);

                return associateMapper.associateToSaveResponse(savedAssociate, accounts);
            } catch (Exception e) {
                log.error("Não foi possivel salvar o associdado. Motivo: {}", e.getMessage());
                throw new SaveEntityException("Não foi possível salvar o associado.");
            }
        }

        throw new BusinessRulesException("Não foi possível salvar. Associado já existe!");
    }

    public FindAssociateResponse findById(Long id) {
        log.info("Buscando associado com o id: {}", id);
        try {
            var associate = associateRepository.findById(id).orElseThrow();
            return associateMapper.associateToFindAssociateResponse(associate);
        } catch (Exception e) {
            log.error("Associdado não encontrado com o id: {}", id);
            throw new FindEntityException("Associdado não encontrado.");
        }
    }

    public UpdateAssociateResponse update(Long id, UpdateAssociateRequest request) {
        var associateResponse = findById(id);
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse);

        log.info("Verificando data da ultima atualização para o id {}", id);
        if (associate.getLastPaycheck().isBefore(LocalDate.now().minusMonths(3))) {
            log.info("Tentando atualizar o associado com o id {}", id);
            try {
                associate.setProfession(request.getProfession());
                associate.setSalary(request.getSalary());
                associate.setLastPaycheck(LocalDate.now());
                associateRepository.save(associate);

                log.info("Associado atualizado com sucesso para o id {}", id);
                return UpdateAssociateResponse.builder()
                        .updated(true)
                        .message(UPDATE_SUCCESS)
                        .build();
            } catch (Exception e) {
                log.error("Não foi possivel atualizar o associdado. Motivo: {}", e.getMessage());
                throw new UpdateEntityException("Não foi possível atualizar o associado.");
            }
        }

        log.info("Associado com o id {} não pode ser atualizado, menos de três meses desde a ultima atualização: {}",
                associate.getId(), associate.getLastPaycheck());
        return UpdateAssociateResponse.builder()
                .updated(false)
                .message(UPDATE_ERROR)
                .build();
    }

    public DeleteAssociateResponse delete(Long id) {
        var associate = findById(id);

        if (hasNoActiveContracts(associate.getId())) {
            log.info("Tentando excluir o associado com id: {}", associate.getId());
            try {
                associateRepository.deleteById(id);

                log.info("Associado excluido com sucesso!");
                return DeleteAssociateResponse.builder()
                        .deleted(true)
                        .message(DELETE_SUCCESS)
                        .build();
            } catch (Exception e) {
                log.error("Não foi possivel deletar o associdado. Motivo: {}", e.getMessage());
                throw new DeleteEntityException("Não foi possível deletar o associado.");
            }
        }

        log.info("Associado não pode ser excluido, pois contém contratos ativos!");
        return DeleteAssociateResponse.builder()
                .deleted(false)
                .message(DELETE_ERROR)
                .build();
    }

    private Boolean hasNoActiveContracts(Long id) {
        return contractService.findContracts(id).isEmpty();
    }

    private void validateBusinessRules(SaveAssociateRequest request) {
        if (!isLegalAge(request.getBirthDate()) || !salaryAboveRequirement(request.getSalary())) {
            throw new BusinessRulesException("Idade ou Salário não atendem o mínimo necessário!");
        }
    }

    private Boolean isLegalAge(LocalDate birthDate) {
        return birthDate.isBefore(LocalDate.now().minusYears(18));
    }

    private Boolean salaryAboveRequirement(BigDecimal salary) {
        return salary.compareTo(MIN_SALARY_ACCEPTABLE) > 0;
    }

}
