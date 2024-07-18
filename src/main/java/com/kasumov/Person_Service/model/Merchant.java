package com.kasumov.Person_Service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table(schema = "person", name = "merchants")
public class Merchant implements Persistable<UUID> {

    @Id
    private UUID id;
    private UUID creator_id;
    private UUID company_id;
    private String company_name;
    private String merchantName;
    private String email;
    private String phone_number;
    private LocalDateTime verified_at;
    private LocalDateTime archived_at;
    private Boolean filled;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
