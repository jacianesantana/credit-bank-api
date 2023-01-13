package com.bank.credit.controller;

import com.bank.credit.annotation.associate.DeleteAssociateStandard;
import com.bank.credit.annotation.associate.FindAssociateStandard;
import com.bank.credit.annotation.associate.SaveAssociateStandard;
import com.bank.credit.annotation.associate.UpdateAssociateStandard;
import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.DeleteAssociateResponse;
import com.bank.credit.controller.response.associate.FindAssociateResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/save")
    @SaveAssociateStandard
    public ResponseEntity<SaveAssociateResponse> save(@Valid @RequestBody SaveAssociateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.save(request));
    }

    @GetMapping("/find/{id}")
    @FindAssociateStandard
    public ResponseEntity<FindAssociateResponse> save(@PathVariable Long id) {
        return ResponseEntity.ok(associateService.findById(id));
    }

    @PatchMapping("/updatePaycheck/{id}")
    @UpdateAssociateStandard
    public ResponseEntity<UpdateAssociateResponse> updatePaycheck(@PathVariable Long id,
                                                                  @Valid @RequestBody UpdateAssociateRequest request) {
        var response = associateService.update(id, request);

        if (response.getUpdated()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @DeleteAssociateStandard
    public ResponseEntity<DeleteAssociateResponse> delete(@PathVariable Long id) {
        var response = associateService.delete(id);

        if (response.getDeleted().equals(true)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
