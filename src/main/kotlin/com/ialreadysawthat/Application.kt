package com.ialreadysawthat

import com.ialreadysawthat.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
  embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
    configureRouting()
    configureSecurity()
    configureHTTP()
    configureTemplating()
    configureSerialization()
  }.start(wait = true)
}
