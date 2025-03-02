package org.example.socialsync.plugins


import io.ktor.client.HttpClient
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.socialsync.auth.jwt.repository.UserDataSource
import org.example.socialsync.auth.jwt.route.authenticate
import org.example.socialsync.auth.jwt.route.getSecretInfo
import org.example.socialsync.auth.jwt.route.signIn
import org.example.socialsync.auth.jwt.route.signUp
import org.example.socialsync.auth.jwt.security.hashing.HashingService
import org.example.socialsync.auth.jwt.security.token.TokenConfig
import org.example.socialsync.auth.jwt.security.token.TokenService
import org.example.socialsync.auth.oauth.repository.OAuthSession
import org.example.socialsync.auth.oauth.routes.oAuth

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
    tokenService: TokenService,
    tokenConfig: TokenConfig,
    httpClient: HttpClient,
    redirects: MutableMap<String, String>,
    oAuthSessions: OAuthSession
) {
    routing {
        signUp(hashingService,userDataSource)
        signIn(hashingService,userDataSource,tokenService,tokenConfig)
        authenticate()
        getSecretInfo()

        oAuth(httpClient, redirects, oAuthSessions )

        get("/") {
            call.respondText("Hello World!")
        }
    }

}