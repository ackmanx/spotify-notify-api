package com.ialreadysawthat

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.ialreadysawthat.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureTemplating()
        configureSerialization()
    }.start(wait = true)
}
