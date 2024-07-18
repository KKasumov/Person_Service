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
@Table(schema = "person", name = "profile_history")
public class UserHistory implements Persistable<UUID> {

    @Id
    private UUID id;
    private LocalDateTime createdAt;
    private String profile_type;
    private String reason;
    private String comment;
    private String changed_values;
    private UUID profile_id;

    @Transient
    private User user;

    @Override
    public boolean isNew() {
        return (createdAt == null);
    }
}
