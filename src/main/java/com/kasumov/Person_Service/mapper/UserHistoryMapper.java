package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.UserHistory;

import com.kasumov.DTO.UserHistoryDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserHistoryMapper {

    UserHistoryDTO convert(UserHistory userHistory);

    @InheritInverseConfiguration
    UserHistory convert(UserHistoryDTO userHistoryDTO);
}