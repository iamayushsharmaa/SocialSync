package org.example.socialsync.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.example.socialsync.auth.oauth.data.UserSession
import io.ktor.server.sessions.*

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
}
