package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.associate.FindAssociateStandard;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.service.AssociateService;
import br.com.sicredi.bank.annotation.associate.DeleteAssociateStandard;
import br.com.sicredi.bank.annotation.associate.SaveAssociateStandard;
import br.com.sicredi.bank.annotation.associate.UpdateAssociateStandard;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateRequest;
import br.com.sicredi.bank.controller.response.associate.DeleteAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociateResponse;
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
