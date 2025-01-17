package com.kds.openchatserver.api.controller

import com.kds.openchatserver.api.domain.request.ChatRequest
import com.kds.openchatserver.api.service.kafka.KafkaService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import java.util.UUID

@Controller
class WebSocketController(private val kafkaService: KafkaService) {

    @MessageMapping("/{id}")
    fun send(@DestinationVariable id: UUID, request: ChatRequest) =
        kafkaService.send(id, request)
}