package com.ialreadysawthat.plugins

import com.adamratzman.spotify.SpotifyScope
import com.adamratzman.spotify.SpotifyUserAuthorization
import com.adamratzman.spotify.endpoints.client.ClientProfileApi
import com.adamratzman.spotify.getSpotifyAuthorizationUrl
import com.adamratzman.spotify.spotifyClientApi
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureSecurity() {

  // This is the ID for my application registered with Spotify
  val clientId = "e466e7a286954e13b501d8e7bc4669cc"
  // This will live in heroku config so it isn't exposed via git, but until then store it here and regen a new key later
  val clientSecret = "76f2fd5049574e09899dcf84810e5fef"
  val redirectUrl =
    if (System.getenv("ENV") == "local") "http://me:8080/callback"
    else "https://spotify-notify-api.herokuapp.com/callback"

  routing {
    get("login") {
      val url: String = getSpotifyAuthorizationUrl(
        SpotifyScope.USER_FOLLOW_READ,
        SpotifyScope.PLAYLIST_MODIFY_PUBLIC,
        SpotifyScope.USER_LIBRARY_READ,
        SpotifyScope.PLAYLIST_READ_PRIVATE,
        clientId = clientId,
        redirectUri = redirectUrl,
      )

      call.respondRedirect(url)
    }

    get("callback") {
      val code = call.request.queryParameters["code"]

      if (code.isNullOrBlank()) {
        println("Code is null or blank: $code")
        call.respond(HttpStatusCode.Unauthorized)
      }

      println("Code is not null or blank: $code")

      val authorization = SpotifyUserAuthorization(authorizationCode = code)

      val api = spotifyClientApi(clientId, clientSecret, redirectUrl, authorization).build()

      call.respond(ClientProfileApi(api).getClientProfile())
    }
  }
}
