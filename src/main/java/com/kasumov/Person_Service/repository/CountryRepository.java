package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface CountryRepository extends R2dbcRepository<Country, UUID> {
}
