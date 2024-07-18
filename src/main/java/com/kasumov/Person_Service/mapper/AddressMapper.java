package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.Address;
import com.kasumov.DTO.AddressDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface AddressMapper {

    AddressDTO convert(Address address);

    @InheritInverseConfiguration
    Address convert(AddressDTO addressDTO);

}
