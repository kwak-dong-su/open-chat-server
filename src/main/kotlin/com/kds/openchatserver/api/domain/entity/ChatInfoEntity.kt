package com.kds.openchatserver.api.domain.entity

import com.kds.openchatserver.api.constants.ChatStatus
import com.kds.openchatserver.api.domain.ImmutableUuidEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.UUID

@Entity(name = "chat_info")
class ChatInfoEntity(
    var name: String,
    var userCount: Int,
    @Enumerated(EnumType.STRING)
    var status: ChatStatus,
    var createdBy: UUID
) : ImmutableUuidEntity() {
    constructor(name: String, createdBy: UUID) : this(
        name = name,
        userCount = 0,
        status = ChatStatus.OPEN,
        createdBy = createdBy
    )
}