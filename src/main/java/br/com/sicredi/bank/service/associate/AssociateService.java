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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static br.com.sicredi.bank.utils.Message.*;

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
                try {
                    var associateEntity = associateMapper.saveRequestToAssociate(saveAssociateRequest);
                    associateRepository.save(associateEntity);

                    var checkingAccount = accountService.create(associateEntity, AccountType.CURRENT);
                    var savesAccount = accountService.create(associateEntity, AccountType.SAVINGS);
                    var accounts = List.of(checkingAccount, savesAccount);

                    var response = associateMapper.associateToSaveAssociateResponse(associateEntity, accounts);
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                } catch (Exception e) {
                    throw new SaveEntityException(ASSOCIATE_SAVE_ERROR);
                }
            }

            throw new BusinessRulesException(ASSOCIATE_BUSINESS_AGE_ERROR);
        }

        throw new BusinessRulesException(ASSOCIATE_BUSINESS_CPF_ERROR);
    }

    public ResponseEntity<FindAssociateResponse> findById(Long id) {
        try {
            var associateEntity = associateRepository.findById(id).orElseThrow();
            var response = associateMapper.associateToFindAssociateResponse(associateEntity);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new FindEntityException(ASSOCIATE_FIND_ERROR);
        }
    }

    public ResponseEntity<UpdateAssociateContactResponse> updateContact(Long id, UpdateAssociateContactRequest request) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        try {
            associateEntity.setPhone(request.getPhone());
            associateEntity.setEmail(request.getEmail());
            associateRepository.save(associateEntity);

            var response = associateMapper.associateToUpdateAssociateContactResponse(associateEntity);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new UpdateEntityException(ASSOCIATE_UPDATE_ERROR);
        }
    }

    public ResponseEntity<UpdateAssociatePaycheckResponse> updatePaycheck(Long id, UpdateAssociatePaycheckRequest request) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        if (associateEntity.getLastPaycheck().isBefore(LocalDate.now().minusMonths(3))) {
            try {
                associateEntity.setProfession(request.getProfession());
                associateEntity.setSalary(request.getSalary());
                associateEntity.setLastPaycheck(LocalDate.now());
                associateRepository.save(associateEntity);

                var response = associateMapper.associateToUpdateAssociatePaycheckResponse(associateEntity);

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                throw new UpdateEntityException(ASSOCIATE_UPDATE_ERROR);
            }
        }

        throw new BusinessRulesException(ASSOCIATE_BUSINESS_PAYCHECK_ERROR);
    }

    public ResponseEntity<Void> delete(Long id) {
        var associateResponse = findById(id);
        var associateEntity = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());
        var contracts = contractService.findContracts(associateEntity);

        if (contracts.isEmpty()) {
            try {
                associateRepository.deleteById(id);

                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                throw new DeleteEntityException(ASSOCIATE_DELETE_ERROR);
            }
        } else {
            throw new BusinessRulesException(ASSOCIATE_BUSINESS_CONTRACT_ERROR);
        }
    }

}
