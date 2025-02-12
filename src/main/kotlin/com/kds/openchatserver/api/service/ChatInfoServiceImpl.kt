package com.kds.openchatserver.api.service

import com.kds.openchatserver.api.constants.ChatStatus
import com.kds.openchatserver.api.constants.messages.ErrorMessage
import com.kds.openchatserver.api.domain.entity.ChatInfoEntity
import com.kds.openchatserver.api.domain.vo.ChatVO
import com.kds.openchatserver.api.exception.NotFoundException
import com.kds.openchatserver.api.repository.ChatInfoRepository
import com.kds.openchatserver.api.service.kafka.KafkaService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class ChatInfoServiceImpl(
    private val chatInfoRepository: ChatInfoRepository,
    private val kafkaService: KafkaService
) : ChatInfoService {
    override fun get(id: UUID): ChatInfoEntity? = chatInfoRepository.findById(id).getOrNull()

    override fun getChatInfo(id: UUID): ChatInfoEntity = get(id) ?: throw NotFoundException(ErrorMessage.UNKNOWN)

    override fun getAll(): List<ChatInfoEntity> = chatInfoRepository.findAll()

    @Transactional
    override fun create(name: String, userId: UUID): ChatInfoEntity {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun increase(id: UUID): ChatInfoEntity {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun decrease(id: UUID): ChatInfoEntity {
        TODO("Not yet implemented")
    }

    override fun publish(id: UUID, userName: String, message: String): Boolean {
        val chat = chatInfoRepository.findByIdAndStatus(id, ChatStatus.OPEN)
            ?: throw NotFoundException(ErrorMessage.UNKNOWN)
        kafkaService.send(chat.id.toString(), ChatVO(userName, message))
        return true
    }
}