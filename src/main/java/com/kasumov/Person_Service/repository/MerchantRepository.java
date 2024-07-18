package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.Merchant;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantRepository extends R2dbcRepository<Merchant, UUID> {
}
