package com.kasumov.Person_Service.mapper;

import com.kasumov.DTO.MerchantMembersDTO;
import com.kasumov.Person_Service.model.MerchantMembers;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MerchantMembersMapper {

    MerchantMembersDTO convert(MerchantMembers merchantMembers);

    @InheritInverseConfiguration
    MerchantMembers convert(MerchantMembersDTO merchantMembersDTO);
}