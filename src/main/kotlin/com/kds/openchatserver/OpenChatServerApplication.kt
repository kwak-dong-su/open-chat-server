package com.kds.openchatserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenChatServerApplication

fun main(args: Array<String>) {
    runApplication<OpenChatServerApplication>(*args)
}
