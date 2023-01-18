package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.associate.*;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociatePaycheckRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociatePaycheckResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociateResponse;
import br.com.sicredi.bank.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/associate")
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/save")
    @SaveAssociateStandard
    public ResponseEntity<SaveAssociateResponse> save(@Valid @RequestBody SaveAssociateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.save(request));
    }

    @GetMapping("/find/{id}")
    @FindAssociateStandard
    public ResponseEntity<FindAssociateResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(associateService.findById(id));
    }

    @PutMapping("/update/{id}")
    @UpdateAssociateStandard
    public ResponseEntity<UpdateAssociateResponse> update(@PathVariable Long id,
                                                          @Valid @RequestBody UpdateAssociateRequest request) {
        return ResponseEntity.ok(associateService.update(id, request));
    }

    @PatchMapping("/updatePaycheck/{id}")
    @UpdateAssociatePaycheckStandard
    public ResponseEntity<UpdateAssociatePaycheckResponse> updatePaycheck(@PathVariable Long id,
                                                                          @Valid @RequestBody UpdateAssociatePaycheckRequest request) {
        return ResponseEntity.ok(associateService.updatePaycheck(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @DeleteAssociateStandard
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        associateService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
