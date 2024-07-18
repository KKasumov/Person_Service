package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.UserHistory;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface UserHistoryRepository extends R2dbcRepository<UserHistory, UUID> {
}
