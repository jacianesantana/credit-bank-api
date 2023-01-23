package br.com.sicredi.bank.service.address;

import br.com.sicredi.bank.model.request.address.AddressRequest;
import br.com.sicredi.bank.model.response.address.AddressResponse;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AddressMapper;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.AddressRepository;
import br.com.sicredi.bank.service.associate.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static br.com.sicredi.bank.utils.Message.ADDRESS_FIND_ERROR;
import static br.com.sicredi.bank.utils.Message.ADDRESS_SAVE_ERROR;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final AssociateService associateService;
    private final AssociateMapper associateMapper;

    public ResponseEntity<AddressResponse> save(Long idAssociate, AddressRequest request) {
        var associateResponse = associateService.findById(idAssociate);
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        try {
            var address = addressMapper.addressRequestToAddress(associate, request);
            var addressSaved = addressRepository.save(address);

            var response = addressMapper.addressToAddressResponse(addressSaved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            throw new SaveEntityException(ADDRESS_SAVE_ERROR);
        }
    }

    public ResponseEntity<AddressResponse> update(Long id, Long idAssociate, AddressRequest request) {
        var associateResponse = associateService.findById(idAssociate);
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse.getBody());

        if (addressRepository.findById(id).isPresent()) {
            var addressEntity = addressRepository.findById(id).orElseThrow();

            if(addressEntity.getAssociate().getId().equals(idAssociate)) {
                var address = addressMapper.updateAddressRequestToAddress(id, associate, request);

                try {
                    var addressSaved = addressRepository.save(address);
                    var response = addressMapper.addressToAddressResponse(addressSaved);

                    return ResponseEntity.ok(response);
                } catch (Exception e) {
                    throw new SaveEntityException(ADDRESS_SAVE_ERROR);
                }
            }
        }

        throw new FindEntityException(ADDRESS_FIND_ERROR);
    }

}
