package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.Individual;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface IndividualRepository extends R2dbcRepository<Individual, UUID> {
    Flux<Object> findByUserId(UUID userId);
}
