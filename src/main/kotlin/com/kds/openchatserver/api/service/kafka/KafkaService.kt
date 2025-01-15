package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.vo.ChatVO

interface KafkaService {
    fun send(topic: String, message: ChatVO)
}