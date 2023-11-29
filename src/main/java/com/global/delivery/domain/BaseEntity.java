package com.global.delivery.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class BaseEntity {
    public BaseEntity() {
    }

    LocalDateTime utcDateTime = Instant.now().atZone(ZoneOffset.UTC).toLocalDateTime();

    public String id = UUID.randomUUID().toString();
    @JsonIgnoreProperties
    private LocalDateTime lastModifiedDate =  utcDateTime;
    private LocalDateTime creationDate = utcDateTime;
    public BaseEntity(String  id) {
        this.id = id;
    }
}

