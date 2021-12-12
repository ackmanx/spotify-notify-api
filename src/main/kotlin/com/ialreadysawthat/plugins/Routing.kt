@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.ialreadysawthat.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.html.body
import kotlinx.html.h1

fun Application.configureRouting() {
  install(Locations)

  routing {
    get("/") {
      call.respondHtml {
        body {
          h1 { +"Big World" }
        }
      }
    }

    static("/static") {
      resources("static")
      defaultResource("static/index.html")
    }
  }
}
