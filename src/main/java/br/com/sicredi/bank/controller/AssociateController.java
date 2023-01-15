package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.associate.DeleteAssociateStandard;
import br.com.sicredi.bank.annotation.associate.FindAssociateStandard;
import br.com.sicredi.bank.annotation.associate.SaveAssociateStandard;
import br.com.sicredi.bank.annotation.associate.UpdateAssociateStandard;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.AssociateResponse;
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
    public ResponseEntity<AssociateResponse> save(@Valid @RequestBody SaveAssociateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.save(request));
    }

    @GetMapping("/find/{id}")
    @FindAssociateStandard
    public ResponseEntity<AssociateResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(associateService.findById(id));
    }

    @PatchMapping("/updatePaycheck/{id}")
    @UpdateAssociateStandard
    public ResponseEntity<AssociateResponse> updatePaycheck(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateAssociateRequest request) {
        return ResponseEntity.ok(associateService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @DeleteAssociateStandard
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        associateService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
