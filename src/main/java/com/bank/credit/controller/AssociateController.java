package com.bank.credit.controller;

import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.DeleteAssociateResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/save")
    public ResponseEntity<SaveAssociateResponse> save(@RequestBody SaveAssociateRequest request) {
        return new ResponseEntity<>(associateService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateAssociateResponse> update(@PathVariable Long id,
                                                          @RequestBody UpdateAssociateRequest request) {
        var response = associateService.update(id, request);

        if (response.getUpdated().equals(true)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteAssociateResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(associateService.delete(id));
    }
}
