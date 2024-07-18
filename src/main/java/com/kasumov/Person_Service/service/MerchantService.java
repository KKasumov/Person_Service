package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.MerchantRepository;
import com.kasumov.Person_Service.mapper.MerchantMapper;
import com.kasumov.DTO.MerchantDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    public MerchantService(MerchantRepository merchantRepository, MerchantMapper merchantMapper) {
        this.merchantRepository = merchantRepository;
        this.merchantMapper = merchantMapper;
    }


    public Mono<MerchantDTO> save(MerchantDTO merchantDTO){
        merchantDTO.setUpdatedAt(LocalDateTime.now());
        merchantDTO.setCreatedAt(LocalDateTime.now());
        return merchantRepository.save(merchantMapper.convert(merchantDTO))
                .map(merchantMapper::convert);
    }


    public Mono<MerchantDTO> getById(UUID id) {
        return merchantRepository.findById(id)
                .map(merchantMapper::convert);
    }

    public Flux<MerchantDTO> getAll() {
        return merchantRepository.findAll()
                .map(merchantMapper::convert);

    }
}
