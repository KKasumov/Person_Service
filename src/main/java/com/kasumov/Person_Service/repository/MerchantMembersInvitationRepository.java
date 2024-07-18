package com.kasumov.Person_Service.repository;

import com.kasumov.Person_Service.model.MerchantMembersInvitation;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import java.util.UUID;

public interface MerchantMembersInvitationRepository extends R2dbcRepository<MerchantMembersInvitation, UUID> {
}
