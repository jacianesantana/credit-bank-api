package br.com.sicredi.bank.service.address;

import br.com.sicredi.bank.controller.request.address.AddressRequest;
import br.com.sicredi.bank.controller.response.address.AddressResponse;
import br.com.sicredi.bank.exception.FindEntityException;
import br.com.sicredi.bank.exception.SaveEntityException;
import br.com.sicredi.bank.mapper.AddressMapper;
import br.com.sicredi.bank.mapper.AssociateMapper;
import br.com.sicredi.bank.repository.AddressRepository;
import br.com.sicredi.bank.service.associate.AssociateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final AssociateService associateService;
    private final AssociateMapper associateMapper;

    public AddressResponse save(Long idAssociate, AddressRequest request) {
        var associateResponse = associateService.findById(idAssociate);
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse);

        try {
            log.info("Criando um endereço para o associado com o id {}", idAssociate);
            var address = addressMapper.addressRequestToAddress(associate, request);
            var addressSaved = addressRepository.save(address);

            return addressMapper.addressToAddressResponse(addressSaved);
        } catch (Exception e) {
            log.error("Não foi possivel salvar o endereço. Motivo: {}", e.getMessage());
            throw new SaveEntityException("Não foi possível salvar o endereço.");
        }
    }

    public AddressResponse update(Long id, Long idAssociate, AddressRequest request) {
        var associateResponse = associateService.findById(idAssociate);
        var associate = associateMapper.findAssociateResponseToAssociate(associateResponse);

        if (addressRepository.findById(id).isPresent()) {
            var addressEntity = addressRepository.findById(id).orElseThrow();

            if(addressEntity.getAssociate().getId().equals(idAssociate)) {
                log.info("Atualizando um endereço para o associado com o id {}", idAssociate);
                var address = addressMapper.updateAddressRequestToAddress(id, associate, request);

                try {
                    var addressSaved = addressRepository.save(address);
                    return addressMapper.addressToAddressResponse(addressSaved);
                } catch (Exception e) {
                    log.error("Não foi possivel salvar o endereço. Motivo: {}", e.getMessage());
                    throw new SaveEntityException("Não foi possível salvar o endereço.");
                }
            }
        }

        log.error("Endereço não encontrado com o id: {}", id);
        throw new FindEntityException("Endereço não encontrado.");
    }

}
