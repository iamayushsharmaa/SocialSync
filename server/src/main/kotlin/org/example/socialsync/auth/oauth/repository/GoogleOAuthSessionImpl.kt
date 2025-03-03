package org.example.socialsync.auth.oauth.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.uri
import io.ktor.server.response.respondRedirect
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions
import org.example.socialsync.auth.oauth.model.UserInfo
import org.example.socialsync.auth.oauth.model.UserSession


class GoogleOAuthSessionImpl: GoogleOAuthSession {

     override suspend fun getPersonalGreeting(
        httpClient: HttpClient,
        userSession: UserSession
    ): UserInfo = httpClient.get("https://www.googleapis.com/oauth2/v2/userinfo") {
        headers {
            append(HttpHeaders.Authorization, "Bearer ${userSession.token}")
        }
    }.body()

    override suspend fun getSession(call: ApplicationCall): UserSession? {
        val userSession: UserSession? = call.sessions.get()
        if (userSession == null) {
            val redirectUrl = URLBuilder("http://0.0.0.0:8080/login").run {
                parameters.append("redirectUrl", call.request.uri)
                build()
            }
            call.respondRedirect(redirectUrl)
            return null
        }
        return userSession
    }
}