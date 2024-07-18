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
@Table(schema = "person", name = "individuals")
public class Individual implements Persistable<UUID>{


    @Id
    private UUID id;
    private UUID user_id;


    private LocalDateTime archived_at;
    private LocalDateTime verified_at;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status;
    private String email;
    private String passport_number;
    private String phone_number;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}