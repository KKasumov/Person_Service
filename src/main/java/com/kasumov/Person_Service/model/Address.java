package com.kasumov.Person_Service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "person", name = "addresses")
public class Address implements Persistable<UUID> {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Id
    private UUID id;
    private UUID country_id;
    private String address;
    private String zipCode;
    private String city;
    private String state;

    @Transient
    private Country country;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
