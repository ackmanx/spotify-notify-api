package com.ialreadysawthat.plugins

import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.getSpotifyAuthorizationUrl
import io.ktor.auth.*
import io.ktor.util.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.locations.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureSecurity() {

  val client_id = "e466e7a286954e13b501d8e7bc4669cc" //this is the ID for my application registered with Spotify
  val client_secret = "76f2fd5049574e09899dcf84810e5fef" //this will live in heroku config so it isn't exposed via git, but until then store it here and regen a new key later
  // val scope = "user-follow-read playlist-modify-public user-library-read playlist-read-private"
  val stateKey = "spotify_auth_state"
  val redirect_url = "http://me:8080/callback"

  routing {
    get("login") {
      val url: String = getSpotifyAuthorizationUrl(
        SpotifyScope.USER_FOLLOW_READ,
        SpotifyScope.PLAYLIST_MODIFY_PUBLIC,
        SpotifyScope.USER_LIBRARY_READ,
        SpotifyScope.PLAYLIST_READ_PRIVATE,
        clientId = client_id,
        redirectUri = redirect_url,
        state = stateKey
      )

      call.respondRedirect(url)
    }

    get("/callback") {
      call.respondRedirect("/location/eric")
    }
  }
}
