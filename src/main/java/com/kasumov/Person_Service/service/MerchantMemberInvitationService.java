package com.kasumov.Person_Service.service;

import com.kasumov.Person_Service.repository.MerchantMembersInvitationRepository;
import com.kasumov.Person_Service.mapper.MerchantMembersInvitationMapper;
import com.kasumov.DTO.MerchantMembersInvitationDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public record MerchantMemberInvitationService(MerchantMembersInvitationRepository merchantMembersInvitationRepository,
                                              MerchantMembersInvitationMapper merchantMembersInvitationMapper) {

    public Mono<MerchantMembersInvitationDTO> save(MerchantMembersInvitationDTO merchantMembersInvitationDTO){
        merchantMembersInvitationDTO.setUpdatedAt(LocalDateTime.now());
        merchantMembersInvitationDTO.setCreatedAt(LocalDateTime.now());
        return merchantMembersInvitationRepository.save(merchantMembersInvitationMapper.convert(merchantMembersInvitationDTO))
                .map(merchantMembersInvitationMapper::convert);
    }

    public Flux<MerchantMembersInvitationDTO> getAll() {
        return merchantMembersInvitationRepository.findAll()
                .map(merchantMembersInvitationMapper::convert);

    }

    public Mono<MerchantMembersInvitationDTO> getById(UUID id){
        return merchantMembersInvitationRepository.findById(id)
                .map(merchantMembersInvitationMapper::convert);
    }

}
