package com.kds.openchatserver.api.service.kafka

import com.kds.openchatserver.api.domain.request.ChatRequest
import com.kds.openchatserver.api.domain.vo.ChatVO
import com.kds.openchatserver.api.domain.vo.TranslationVO
import com.kds.openchatserver.api.service.webclient.TranslationService
import com.kds.openchatserver.utils.log
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
    private val messagingTemplate: SimpMessagingTemplate,
    private val translationService: TranslationService
) : KafkaService {
    override fun send(topic: String, message: ChatVO) {
        TODO("Not yet implemented")
    }

    override fun send(topic: String, message: TranslationVO) {
        kafkaTemplate.send(topic, message)
        log().info("Sent message: $message")
    }

    override fun send(chatId: UUID, request: ChatRequest) =
        translationService.translate(request.message).also {
            it.userName = request.userName
        }.run { send(chatId.toString(), this) }

    @KafkaListener(topicPattern = ".*", groupId = "chat")
    override fun listenAll(@Header(value = KafkaHeaders.RECEIVED_TOPIC) topic: String, message: TranslationVO) {
        log().info("Received messages from topic $topic, Received message: $message")
        messagingTemplate.convertAndSend("/sub/${topic}", message)
    }
}