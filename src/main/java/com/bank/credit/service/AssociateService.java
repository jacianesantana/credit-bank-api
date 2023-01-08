package com.bank.credit.service;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.DeleteAssociateResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.exception.DeleteEntityException;
import com.bank.credit.exception.FindEntityException;
import com.bank.credit.exception.SaveEntityException;
import com.bank.credit.exception.UpdateEntityException;
import com.bank.credit.mapper.AssociateMapper;
import com.bank.credit.model.Associate;
import com.bank.credit.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateService {

    private static final String UPDATE_SUCCESS = "Associado atualizado com sucesso!";
    private static final String UPDATE_ERROR = "Associado com menos de 3 meses desde da última atualização!";
    private static final String DELETE_SUCCESS = "Associado excluido com sucesso!";
    private static final String DELETE_ERROR = "Associado contém contratos ativos!";

    private final AssociateRepository associateRepository;
    private final AssociateMapper associateMapper;
    private final ContractService contractService;

    public SaveAssociateResponse save(SaveAssociateRequest request) {
        log.info("Tentando salvar o associdado com cpf {}", request.getCpf().substring(0, 4).concat("..."));
        try {
            var entity = associateMapper.saveRequestToAssociate(request);
            var savedAssociate = associateRepository.save(entity);

            log.info("Associado salvo com sucesso. Id gerado: {}", savedAssociate.getId());
            return associateMapper.associateToSaveResponse(savedAssociate);
        } catch (Exception e) {
            log.error("Não foi possivel salvar o associdado. Motivo: {}", e.getMessage());
            throw new SaveEntityException("Não foi possível salvar o associado.");
        }
    }

    public UpdateAssociateResponse update(Long id, UpdateAssociateRequest request) {
        var associate = findById(id);

        log.info("Verificando data da ultima atualização para o id {}", id);
        if (associate.getLastPaycheck().isBefore(LocalDate.now().minusMonths(3))) {
            log.info("Tentando atualizar o associado com o id {}", id);
            try {
                var entity = associateMapper.updateRequestToAssociate(request);
                associateRepository.save(entity);

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

        if (!hasActiveContracts(associate.getId())) {
            log.info("Tentando excluir o associado com id: {}", associate.getId());
            try {
                associateRepository.deleteById(id);

                log.info("Associado excluido com sucesso!");
                return DeleteAssociateResponse.builder()
                        .deleted(true)
                        .message(DELETE_SUCCESS)
                        .build();
            } catch (Exception e) {
                log.error("Não foi possivel atualizar o associdado. Motivo: {}", e.getMessage());
                throw new DeleteEntityException("Não foi possível atualizar o associado.");
            }
        }

        log.info("Associado não pode ser excluido, pois contém contratos ativos!");
        return DeleteAssociateResponse.builder()
                .deleted(false)
                .message(DELETE_ERROR)
                .build();
    }

    public Associate findById(Long id) {
        log.info("Buscando associado com o id: {}", id);
        try {
            return associateRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            log.error("Associdado não encontrado com o id: {}", id);
            throw new FindEntityException("Associdado não encontrado.");
        }
    }

    private Boolean hasActiveContracts(Long id) {
        return contractService.findContracts(id).isEmpty();
    }
}
