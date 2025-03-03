package org.example.socialsync

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.example.socialsync.app.SERVER_PORT
import org.example.socialsync.auth.jwt.repository.UserDataSource
import org.example.socialsync.auth.jwt.security.hashing.HashingService
import org.example.socialsync.auth.jwt.security.token.TokenConfig
import org.example.socialsync.auth.jwt.security.token.TokenService
import org.example.socialsync.auth.oauth.repository.GoogleOAuthSession
import org.example.socialsync.di.mainModule
import org.example.socialsync.plugins.configureMonitoring
import org.example.socialsync.plugins.configureRouting
import org.example.socialsync.plugins.configureSecurity
import org.example.socialsync.plugins.configureSessions
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger


val applicationHttpClient = HttpClient(CIO) {
    install(ContentNegotiation){
        json()
    }
}
fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module(httpClient: HttpClient = applicationHttpClient) {

    install(Koin) {
        slf4jLogger()
        modules(mainModule)
    }
    val userDataSource by inject<UserDataSource>()


    val tokenService by inject<TokenService>()
    val tokenConfig = TokenConfig(
        issuer = "http://0.0.0.0:8080",
        audience = "jwt-audience",
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService by inject<HashingService>()

    val oAuthSessions by inject<GoogleOAuthSession>()
    val redirects = mutableMapOf<String, String>()

    configureSecurity(tokenConfig, httpClient, redirects)
    configureMonitoring()
    configureRouting(hashingService, userDataSource, tokenService, tokenConfig, httpClient, redirects, oAuthSessions)
    configureSessions()

}