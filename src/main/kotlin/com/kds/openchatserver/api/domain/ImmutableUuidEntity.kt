package com.kds.openchatserver.api.domain

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ImmutableUuidEntity : UuidEntity() {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(updatable = false, nullable = false)
    @CreatedDate
    var createdAt: Instant = Instant.MIN
        protected set
}