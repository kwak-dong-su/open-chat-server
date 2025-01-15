package com.kds.openchatserver.api.service

import com.kds.openchatserver.api.constants.messages.ErrorMessage
import com.kds.openchatserver.api.domain.entity.ChatInfoEntity
import com.kds.openchatserver.api.exception.NotFoundException
import com.kds.openchatserver.api.repository.ChatInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class ChatInfoServiceImpl(private val chatInfoRepository: ChatInfoRepository) : ChatInfoService {
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
}