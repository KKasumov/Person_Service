package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.Individual;
import com.kasumov.DTO.IndividualDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndividualMapper {

    IndividualDTO convert(Individual individual);

    @InheritInverseConfiguration
    Individual convert(IndividualDTO individualDTO);
}
