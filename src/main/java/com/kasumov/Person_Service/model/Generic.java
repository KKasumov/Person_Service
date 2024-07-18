package com.kasumov.Person_Service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class Generic {

    @Id
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
