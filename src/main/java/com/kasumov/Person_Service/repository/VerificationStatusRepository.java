package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.VerificationStatus;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface VerificationStatusRepository extends R2dbcRepository<VerificationStatus, UUID> {
}
