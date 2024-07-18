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
@Table(schema = "person", name = "merchant_members")
public class MerchantMembers implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID user_id;
    private UUID merchant_id;
    private String memberRole;
    private String phone_number;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
