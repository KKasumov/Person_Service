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
@Table(schema = "person", name = "users")
public class User implements Persistable<UUID> {

    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String secret_key;
    private String first_name;
    private String last_name;
    private String email;
    private String changed_values;
    private LocalDateTime verified_at;
    private LocalDateTime archived_at;
    private String status;
    private boolean filled;
    private UUID address_id;

    @Override

    public boolean isNew() {
        return (createdAt == null);
    }

}
