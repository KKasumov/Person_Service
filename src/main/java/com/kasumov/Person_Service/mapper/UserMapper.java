package com.kasumov.Person_Service.mapper;

import com.kasumov.Person_Service.model.User;
import com.kasumov.DTO.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO convert(User user);

    @InheritInverseConfiguration
    User convert(UserDTO userDTO);

}


