package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.MerchantMembersInvitation;
import com.kasumov.DTO.MerchantMembersInvitationDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface MerchantMembersInvitationMapper {

    MerchantMembersInvitationDTO convert(MerchantMembersInvitation merchantMembersInvitation);

    @InheritInverseConfiguration
    MerchantMembersInvitation convert(MerchantMembersInvitationDTO merchantMembersInvitationDTO);
}