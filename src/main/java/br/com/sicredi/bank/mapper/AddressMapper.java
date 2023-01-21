package br.com.sicredi.bank.mapper;

import br.com.sicredi.bank.model.request.address.AddressRequest;
import br.com.sicredi.bank.model.response.address.AddressResponse;
import br.com.sicredi.bank.model.entity.AddressEntity;
import br.com.sicredi.bank.model.entity.AssociateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressEntity addressRequestToAddress(AssociateEntity associate, AddressRequest request) {
        return AddressEntity.builder()
                .associate(associate)
                .zipCode(request.getZipCode())
                .streetName(request.getStreetName())
                .number(request.getNumber())
                .complement(request.getComplement())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .build();
    }

    public AddressResponse addressToAddressResponse(AddressEntity address) {
        return AddressResponse.builder()
                .id(address.getId())
                .zipCode(address.getZipCode())
                .streetName(address.getStreetName())
                .number(address.getNumber())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

    public AddressEntity updateAddressRequestToAddress(Long id, AssociateEntity associate, AddressRequest request) {
        return AddressEntity.builder()
                .id(id)
                .associate(associate)
                .zipCode(request.getZipCode())
                .streetName(request.getStreetName())
                .number(request.getNumber())
                .complement(request.getComplement())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .build();
    }

}
