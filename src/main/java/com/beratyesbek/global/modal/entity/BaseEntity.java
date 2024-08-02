package com.beratyesbek.global.modal.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Version;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.MappedSuperclass;


import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    public static final Boolean DEFAULT_DELETED_VALUE = false;

    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = DEFAULT_DELETED_VALUE;

    @PrePersist
    protected void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }
}
