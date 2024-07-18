package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.AddressRepository;
import com.kasumov.Person_Service.mapper.AddressMapper;
import com.kasumov.DTO.AddressDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Mono<AddressDTO> saveAddress(AddressDTO addressDTO) {
        addressDTO.setUpdatedAt(LocalDateTime.now());
        addressDTO.setCreatedAt(LocalDateTime.now());
        return addressRepository.save(addressMapper.convert(addressDTO))
                .map(addressMapper::convert);
    }

    public Flux<AddressDTO> getAll() {
        return addressRepository.findAll()
                .map(addressMapper::convert);
    }

    public Mono<AddressDTO> getById(UUID id) {
        return addressRepository.findById(id)
                .map(addressMapper::convert);
    }

    public Mono<Void> deleteAddress(UUID id) {
        return addressRepository.deleteById(id);
    }

}