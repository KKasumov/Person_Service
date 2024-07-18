package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.MerchantMembersRepository;
import com.kasumov.Person_Service.mapper.MerchantMembersMapper;
import com.kasumov.DTO.MerchantMembersDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MerchantMembersService {

    private final MerchantMembersRepository merchantMembersRepository;
    private final MerchantMembersMapper merchantMembersMapper;

    public MerchantMembersService(MerchantMembersRepository merchantMembersRepository, MerchantMembersMapper merchantMembersMapper) {
        this.merchantMembersRepository = merchantMembersRepository;
        this.merchantMembersMapper = merchantMembersMapper;
    }


    public Mono<MerchantMembersDTO> save(MerchantMembersDTO merchantMembersDTO){
        merchantMembersDTO.setUpdatedAt(LocalDateTime.now());
        merchantMembersDTO.setCreatedAt(LocalDateTime.now());
        return merchantMembersRepository.save(merchantMembersMapper.convert(merchantMembersDTO))
                .map(merchantMembersMapper::convert);
    }

    public Flux<MerchantMembersDTO> getAll() {
        return merchantMembersRepository.findAll()
                .map(merchantMembersMapper::convert);

    }

    public Mono<MerchantMembersDTO> getById(UUID id) {
        return merchantMembersRepository.findById(id)
                .map(merchantMembersMapper::convert);
    }

}
