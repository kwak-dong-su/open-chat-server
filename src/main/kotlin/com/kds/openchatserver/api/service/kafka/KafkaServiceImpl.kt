package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.request.ChatRequest
import com.kds.openchatserver.api.domain.vo.ChatVO
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaServiceImpl(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val messagingTemplate: SimpMessagingTemplate
) : KafkaService {

    override fun send(topic: String, message: ChatVO) {
        kafkaTemplate.send(topic, message)
        println("Sent message: $message")
    }

    override fun send(chatId: UUID, request: ChatRequest) =
        ChatVO(request.userName, request.message).run { send(chatId.toString(), this) }

    @KafkaListener(topicPattern = ".*", groupId = "chat")
    override fun listenAll(@Header(value = KafkaHeaders.RECEIVED_TOPIC) topic: String, message: ChatVO) {
        println("Received messages from topic $topic")
        println("Received message: $message")
        messagingTemplate.convertAndSend("/sub/${topic}", message)
    }
}