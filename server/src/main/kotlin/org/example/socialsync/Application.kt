package org.example.socialsync

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.socialsync.app.SERVER_PORT
import org.example.socialsync.auth.data.models.AuthRequest
import org.example.socialsync.auth.data.repository.UserDataSource
import org.example.socialsync.auth.data.repository.UserDataSourceImpl
import org.example.socialsync.auth.security.hashing.SHA256HashingServiceImpl
import org.example.socialsync.auth.security.token.JwtTokenService
import org.example.socialsync.auth.security.token.TokenConfig
import org.example.socialsync.di.mainModule
import org.example.socialsync.plugins.configureMonitoring
import org.example.socialsync.plugins.configureRouting
import org.example.socialsync.plugins.configureSecurity
import org.example.socialsync.plugins.configureSerialization
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    install(Koin) {
        slf4jLogger()
        modules(mainModule)
    }
    val userDataSource by inject<UserDataSource>()

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = "http://0.0.0.0:8080",
        audience = "jwt-audience",
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )


    val hashingService = SHA256HashingServiceImpl()

    configureSerialization()
    configureSecurity(tokenConfig)
    configureMonitoring()
    configureRouting(hashingService, userDataSource, tokenService, tokenConfig)

}