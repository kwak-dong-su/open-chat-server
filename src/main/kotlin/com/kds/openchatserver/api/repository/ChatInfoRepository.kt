package com.kds.openchatserver.api.repository

import com.kds.openchatserver.api.domain.entity.ChatInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ChatInfoRepository : JpaRepository<ChatInfoEntity, UUID> {
}