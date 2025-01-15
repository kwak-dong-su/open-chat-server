package com.kds.openchatserver.api.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.domain.Persistable
import java.util.*

@MappedSuperclass
abstract class UuidEntity : Persistable<UUID> {
    @Id
    @Column(updatable = false, columnDefinition = "uuid")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @GeneratedValue(generator = "system")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private var id: UUID? = null

    override fun getId(): UUID? = id

    @JsonIgnore
    override fun isNew(): Boolean {
        return id == null
    }
}