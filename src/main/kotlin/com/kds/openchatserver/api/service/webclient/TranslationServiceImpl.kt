package com.kds.openchatserver.api.service.webclient

import com.kds.openchatserver.api.domain.vo.TranslationVO
import com.kds.openchatserver.utils.log
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TranslationServiceImpl : TranslationService {

    @Value("\${translation.server.url}")
    lateinit var url: String

    override fun translate(text: String): TranslationVO {
        val result = WebClient.create().get()
            .uri("$url/translate?message=$text")
            .retrieve()
            .bodyToMono(TranslationVO::class.java)
            .block()
        log().info("result: $result")
        return result!!
    }
}