package br.com.sicredi.bank.builder;

import br.com.sicredi.bank.model.request.address.AddressRequest;
import br.com.sicredi.bank.model.response.address.AddressResponse;
import br.com.sicredi.bank.model.entity.AddressEntity;

import static br.com.sicredi.bank.builder.AssociateBuilder.buildAssociate;

public class AddressBuilder {

    public static AddressRequest buildAddressRequest() {
        return AddressRequest.builder()
                .zipCode("anyZipCode")
                .streetName("anyStreetName")
                .number("anyNumber")
                .complement("anyComplement")
                .city("anyCity")
                .state("anyState")
                .country("anyCountry")
                .build();
    }

    public static AddressResponse buildAddressResponse() {
        return AddressResponse.builder()
                .id(1L)
                .zipCode("anyZipCode")
                .streetName("anyStreetName")
                .number("anyNumber")
                .complement("anyComplement")
                .city("anyCity")
                .state("anyState")
                .country("anyCountry")
                .build();
    }

    public static AddressEntity buildAddress() {
        return AddressEntity.builder()
                .id(1L)
                .zipCode("anyZipCode")
                .streetName("anyStreetName")
                .number("anyNumber")
                .complement("anyComplement")
                .city("anyCity")
                .state("anyState")
                .country("anyCountry")
                .associate(buildAssociate())
                .build();
    }

}
