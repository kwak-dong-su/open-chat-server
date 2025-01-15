package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.vo.ChatVO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaServiceImpl(private val kafkaTemplate: KafkaTemplate<String, Any>) : KafkaService {

    override fun send(topic: String, message: ChatVO) {
        kafkaTemplate.send(topic, message)
    }
}