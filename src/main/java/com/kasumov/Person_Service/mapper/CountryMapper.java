package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.Country;
import com.kasumov.DTO.CountryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO convert(Country country);

    @InheritInverseConfiguration
    Country convert(CountryDTO countryDTO);

}

