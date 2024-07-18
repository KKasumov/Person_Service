package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.MerchantMembers;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantMembersRepository extends R2dbcRepository<MerchantMembers, UUID>{


}
