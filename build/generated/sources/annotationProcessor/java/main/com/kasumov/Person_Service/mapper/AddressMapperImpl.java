package com.kasumov.Person_Service.mapper;

import com.kasumov.DTO.AddressDTO;
import com.kasumov.Person_Service.model.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-18T19:59:26+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public AddressDTO convert(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.id( address.getId() );
        addressDTO.createdAt( address.getCreatedAt() );
        addressDTO.updatedAt( address.getUpdatedAt() );
        addressDTO.address( address.getAddress() );
        addressDTO.zipCode( address.getZipCode() );
        addressDTO.city( address.getCity() );
        addressDTO.state( address.getState() );

        return addressDTO.build();
    }

    @Override
    public Address convert(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.createdAt( addressDTO.getCreatedAt() );
        address.updatedAt( addressDTO.getUpdatedAt() );
        address.id( addressDTO.getId() );
        address.address( addressDTO.getAddress() );
        address.zipCode( addressDTO.getZipCode() );
        address.city( addressDTO.getCity() );
        address.state( addressDTO.getState() );

        return address.build();
    }
}
