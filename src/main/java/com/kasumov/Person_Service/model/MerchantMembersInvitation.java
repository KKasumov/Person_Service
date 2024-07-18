package com.kasumov.Person_Service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "person", name = "merchant_members_invitations")
public class MerchantMembersInvitation implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID merchant_id;
    private LocalDateTime expires;
    private String first_name;
    private String last_name;
    private String email;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
