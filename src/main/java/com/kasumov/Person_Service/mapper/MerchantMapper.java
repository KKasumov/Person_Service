package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.Merchant;
import com.kasumov.DTO.MerchantDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantDTO convert(Merchant merchant);

    @InheritInverseConfiguration
    Merchant convert(MerchantDTO merchantDTO);
}