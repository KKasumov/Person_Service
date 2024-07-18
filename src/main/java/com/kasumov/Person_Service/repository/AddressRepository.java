package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.Address;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface AddressRepository extends R2dbcRepository<Address, UUID> {
}
