package com.kds.openchatserver.api.controller

import com.kds.openchatserver.api.service.ChatInfoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable

@RestController
@RequestMapping("/api/chat")
class ChatInfoController(private val chatInfoService: ChatInfoService) : BaseController() {

    @GetMapping
    fun list() = Callable { wrap(chatInfoService.getAll()) }

}