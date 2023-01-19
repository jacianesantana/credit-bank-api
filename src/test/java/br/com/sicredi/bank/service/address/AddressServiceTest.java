package br.com.sicredi.bank.service.address;

import br.com.sicredi.bank.controller.request.address.AddressRequest;
import br.com.sicredi.bank.controller.response.associate.FindAssociateResponse;
import br.com.sicredi.bank.entity.AddressEntity;
import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.mapper.AddressMapper;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.AddressRepository;
import br.com.sicredi.bank.service.associate.AssociateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.sicredi.bank.builder.AddressBuilder.*;
import static br.com.sicredi.bank.builder.AssociateBuilder.buildAssociate;
import static br.com.sicredi.bank.builder.AssociateBuilder.buildFindAssociateResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private AssociateService associateService;

    @Mock
    private AssociateMapper associateMapper;

    @Test
    void saveSuccess() {
        var findAssociateResponse = buildFindAssociateResponse();
        var associate = buildAssociate();
        var addressRequest = buildAddressRequest();
        var address = buildAddress();
        var addressResponse = buildAddressResponse();

        when(associateService.findById(anyLong())).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(addressMapper.addressRequestToAddress(any(AssociateEntity.class), any(AddressRequest.class))).thenReturn(address);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(address);
        when(addressMapper.addressToAddressResponse(any(AddressEntity.class))).thenReturn(addressResponse);

        var response = addressService.save(associate.getId(), addressRequest);

        assertEquals(findAssociateResponse.getId(), associate.getId());
        assertEquals(addressRequest.getStreetName(), response.getStreetName());
        assertEquals(addressRequest.getCity(), response.getCity());
    }

    @Test
    void updateSuccess() {
        var findAssociateResponse = buildFindAssociateResponse();
        var associate = buildAssociate();
        var addressRequest = buildAddressRequest();
        var address = buildAddress();
        var addressResponse = buildAddressResponse();

        when(associateService.findById(anyLong())).thenReturn(findAssociateResponse);
        when(associateMapper.findAssociateResponseToAssociate(any(FindAssociateResponse.class))).thenReturn(associate);
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        when(addressMapper.updateAddressRequestToAddress(anyLong(), any(AssociateEntity.class), any(AddressRequest.class))).thenReturn(address);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(address);
        when(addressMapper.addressToAddressResponse(any(AddressEntity.class))).thenReturn(addressResponse);

        var response = addressService.update(address.getId(), associate.getId(), addressRequest);

        assertEquals(findAssociateResponse.getId(), associate.getId());
        assertEquals(address.getId(), response.getId());
        assertEquals(addressRequest.getStreetName(), response.getStreetName());
        assertEquals(addressRequest.getCity(), response.getCity());
    }

}