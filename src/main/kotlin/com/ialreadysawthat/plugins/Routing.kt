package com.ialreadysawthat.plugins

import com.ialreadysawthat.templates.Index
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.routing.*

fun Application.configureRouting() {
  install(Locations)

  routing {
    get("/") {
      call.respondHtmlTemplate(Index()) {}
    }

    static("/static") {
      resources("static")
      defaultResource("static/index.html")
    }
  }
}
