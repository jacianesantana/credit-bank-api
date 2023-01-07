package com.bank.credit.controller;

import com.bank.credit.controller.request.associate.DeleteAssociateRequest;
import com.bank.credit.controller.request.associate.SaveAssociateRequest;
import com.bank.credit.controller.request.associate.UpdateAssociateRequest;
import com.bank.credit.controller.response.associate.DeleteAssociateResponse;
import com.bank.credit.controller.response.associate.SaveAssociateResponse;
import com.bank.credit.controller.response.associate.UpdateAssociateResponse;
import com.bank.credit.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/save")
    public ResponseEntity<SaveAssociateResponse> save(@RequestBody SaveAssociateRequest request) {
        return ResponseEntity.ok().body(associateService.save(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateAssociateResponse> update(@PathVariable String id,
                                                          @RequestBody UpdateAssociateRequest request) {
        return ResponseEntity.ok().body(associateService.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteAssociateResponse> delete(@PathVariable String id,
                                                          @RequestBody DeleteAssociateRequest request) {
        return ResponseEntity.ok().body(associateService.delete(id, request));
    }
}
