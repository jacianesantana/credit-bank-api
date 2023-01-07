package com.bank.credit.service;

import com.bank.credit.controller.request.associate.DeleteAssociateRequest;
import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.DeleteAssociateResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.repository.AssociateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssociateService {

    private final AssociateRepository associateRepository;

    public SaveAssociateResponse save(SaveAssociateRequest request) {
        return new SaveAssociateResponse();
    }

    public UpdateAssociateResponse update(String id, UpdateAssociateRequest request) {
        return new UpdateAssociateResponse();
    }

    public DeleteAssociateResponse delete(String id, DeleteAssociateRequest request) {
        return new DeleteAssociateResponse();
    }
}
