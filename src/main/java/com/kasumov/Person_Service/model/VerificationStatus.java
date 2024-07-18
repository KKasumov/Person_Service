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
@Table(schema = "person", name = "verification_statuses")
public class VerificationStatus implements Persistable <UUID> {


    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String profileType;
    private String details;
    private String verificationStatus;
    private UUID profile_id;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }


}
