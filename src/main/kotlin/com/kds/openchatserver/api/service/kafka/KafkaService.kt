package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.request.ChatRequest
import com.kds.openchatserver.api.domain.vo.ChatVO
import java.util.UUID

interface KafkaService {
    fun send(topic: String, message: ChatVO)
    fun send(chatId: UUID, request: ChatRequest)
    fun listenAll(topic: String, message: ChatVO)
}