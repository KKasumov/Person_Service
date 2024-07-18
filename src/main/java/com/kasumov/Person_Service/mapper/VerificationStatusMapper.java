package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.VerificationStatus;
import com.kasumov.DTO.VerificationStatusDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface VerificationStatusMapper {

    VerificationStatusDTO convert(VerificationStatus verificationStatus);

    @InheritInverseConfiguration
    VerificationStatus convert(VerificationStatusDTO verificationStatusDTO);
}