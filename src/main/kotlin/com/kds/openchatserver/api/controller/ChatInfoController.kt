package com.kds.openchatserver.api.controller

import com.kds.openchatserver.api.domain.request.ChatRequest
import com.kds.openchatserver.api.service.ChatInfoService
import org.springframework.web.bind.annotation.*
import java.util.UUID
import java.util.concurrent.Callable

@RestController
@RequestMapping("/api/chat")
class ChatInfoController(private val chatInfoService: ChatInfoService) : BaseController() {

    @GetMapping
    fun list() = Callable { wrap(chatInfoService.getAll()) }

    @PostMapping("/{id}")
    fun send(@PathVariable id: UUID, @RequestBody request: ChatRequest) =
        Callable { wrap(chatInfoService.publish(id, request.userName, request.message)) }
}