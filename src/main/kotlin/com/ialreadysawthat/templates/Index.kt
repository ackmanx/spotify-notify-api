package com.ialreadysawthat.templates

import io.ktor.html.*
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.h1

class Index : Template<HTML> {
  override fun HTML.apply() {
    body {
      h1 { +"Big World" }
    }
  }
}
