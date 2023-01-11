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

@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/save")
    @SaveAssociateStandard
    public ResponseEntity<SaveAssociateResponse> save(@RequestBody SaveAssociateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.save(request));
    }

//    @GetMapping("/find/{id}")
//    @FindAssociateStandard
//    public ResponseEntity<FindAssociateResponse> save(@PathVariable Long id) {
//        return ResponseEntity.ok(associateService.findById(id));
//    }

//    @PutMapping("/update/{id}")
//    @UpdateAssociateStandard
//    public ResponseEntity<UpdateAssociateResponse> update(@PathVariable Long id,
//                                                          @RequestBody UpdateAssociateRequest request) {
//        var response = associateService.update(id, request);
//
//        if (response.getUpdated().equals(true)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.badRequest().body(response);
//    }

//    @DeleteMapping("/delete/{id}")
//    @DeleteAssociateStandard
//    public ResponseEntity<DeleteAssociateResponse> delete(@PathVariable Long id) {
//        var response = associateService.delete(id);
//
//        if (response.getDeleted().equals(true)) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }
}
