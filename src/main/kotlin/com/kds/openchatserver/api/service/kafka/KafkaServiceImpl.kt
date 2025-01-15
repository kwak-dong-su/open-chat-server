package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.vo.ChatVO
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class KafkaServiceImpl(private val kafkaTemplate: KafkaTemplate<String, Any>) : KafkaService {

    override fun send(topic: String, message: ChatVO) {
        kafkaTemplate.send(topic, message)
        println("Sent message: $message")
    }

    @KafkaListener(topicPattern = ".*", groupId = "chat")
    override fun listenAll(@Header(value = KafkaHeaders.RECEIVED_TOPIC) topic: String, message: ChatVO) {
        println("Received messages from topic $topic")
        println("Received message: $message")
    }
}