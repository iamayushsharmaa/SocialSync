package org.example.socialsync.auth.oauth.routes

import io.ktor.client.HttpClient
import io.ktor.server.auth.OAuthAccessTokenResponse
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import org.example.socialsync.auth.oauth.model.UserInfo
import org.example.socialsync.auth.oauth.model.UserSession
import org.example.socialsync.auth.oauth.repository.OAuthSession

fun Route.oAuth(
    httpClient: HttpClient,
    redirects: MutableMap<String, String>,
    oAuthSessions: OAuthSession
){
    authenticate {
        get("login") {
            call.respondRedirect("/callback")
        }
        get("callback") {
            val currentPrincipal: OAuthAccessTokenResponse.OAuth2? = call.principal()

            currentPrincipal?.let { principal ->
                principal.state?.let { state ->
                    call.sessions.set(UserSession(state, principal.accessToken))
                    redirects[state]?.let { redirect ->
                        call.respondRedirect(redirect)
                        return@get
                    }
                }
            }
            call.respondRedirect("/home")
        }
        get("/home") {
            val userSession: UserSession? = oAuthSessions.getSession(call)
            if (userSession != null) {
                val userInfo: UserInfo = oAuthSessions.getPersonalGreeting(httpClient, userSession)
                call.respondText("Hello, ${userInfo.name}! Welcome home!")
            }
        }
        get("/{path}") {
            val userSession: UserSession? = oAuthSessions.getSession(call)
            if (userSession != null) {
                val userInfo: UserInfo = oAuthSessions.getPersonalGreeting(httpClient, userSession)
                call.respondText("Hello, ${userInfo.name}!")
            }
        }
    }
}