package com.kds.openchatserver.api.service

import com.kds.openchatserver.api.domain.entity.ChatInfoEntity
import java.util.UUID

interface ChatInfoService {
    fun get(id: UUID): ChatInfoEntity?
    fun getChatInfo(id: UUID): ChatInfoEntity
    fun getAll(): List<ChatInfoEntity>
    fun create(name: String, userId: UUID): ChatInfoEntity
    fun increase(id: UUID): ChatInfoEntity
    fun decrease(id: UUID): ChatInfoEntity
}