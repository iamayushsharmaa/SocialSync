package org.example.socialsync.plugins


import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.socialsync.auth.data.repository.UserDataSource
import org.example.socialsync.auth.data.route.authenticate
import org.example.socialsync.auth.data.route.getSecretInfo
import org.example.socialsync.auth.data.route.signIn
import org.example.socialsync.auth.data.route.signUp
import org.example.socialsync.auth.security.hashing.HashingService
import org.example.socialsync.auth.security.token.TokenConfig
import org.example.socialsync.auth.security.token.TokenService

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {
        signUp(hashingService,userDataSource)
        signIn(hashingService,userDataSource,tokenService,tokenConfig)
        authenticate()
        getSecretInfo()

        get("/") {
            call.respondText("Hello World!")
        }
    }

}