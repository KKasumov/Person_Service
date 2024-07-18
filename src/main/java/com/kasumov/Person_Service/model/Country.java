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
@Table(schema = "person", name = "countries")
public class Country implements Persistable<UUID> {


    @Id
    private UUID id;
    private String name;
    private String alpha2;
    private String alpha3;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String state;
    private String status;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
