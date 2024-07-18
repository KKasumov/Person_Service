package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserRepository extends R2dbcRepository<User, UUID> {
}
