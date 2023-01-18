package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.associate.*;
import br.com.sicredi.bank.controller.request.address.AddressRequest;
import br.com.sicredi.bank.controller.request.associate.SaveAssociateRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociateContactRequest;
import br.com.sicredi.bank.controller.request.associate.UpdateAssociatePaycheckRequest;
import br.com.sicredi.bank.controller.response.address.AddressResponse;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.SaveAssociateResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociateContactResponse;
import br.com.sicredi.bank.controller.response.associate.UpdateAssociatePaycheckResponse;
import br.com.sicredi.bank.service.AddressService;
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
    private final AddressService addressService;

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

    @PatchMapping("/updateContact/{id}")
    @UpdateAssociateContactStandard
    public ResponseEntity<UpdateAssociateContactResponse> updateContact(@PathVariable Long id,
                                                                         @Valid @RequestBody UpdateAssociateContactRequest request) {
        return ResponseEntity.ok(associateService.updateContact(id, request));
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

    @PostMapping("/address/save")
    @SaveAssociateAddressStandard
    public ResponseEntity<AddressResponse> saveAddress(@RequestParam Long idAssociate, @Valid @RequestBody AddressRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(idAssociate, request));
    }

    @PutMapping("/address/update/{id}")
    @UpdateAssociateAddressStandard
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @RequestParam Long idAssociate, @Valid @RequestBody AddressRequest request) {
        return ResponseEntity.ok().body(addressService.update(id, idAssociate, request));
    }

}
