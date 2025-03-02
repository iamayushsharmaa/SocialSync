package org.example.socialsync.auth.jwt.route


import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.example.socialsync.auth.jwt.models.AuthRequest
import org.example.socialsync.auth.jwt.models.AuthResponse
import org.example.socialsync.auth.jwt.models.User
import org.example.socialsync.auth.jwt.repository.UserDataSource
import org.example.socialsync.auth.jwt.security.hashing.HashingService
import org.example.socialsync.auth.jwt.security.hashing.SaltedHash
import org.example.socialsync.auth.jwt.security.token.TokenClaim
import org.example.socialsync.auth.jwt.security.token.TokenConfig
import org.example.socialsync.auth.jwt.security.token.TokenService


fun Route.signUp(
    hashingService: HashingService,
    userDataSource: UserDataSource
) {
    post("signup") {
        val request = call.receiveNullable<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest, "Invalid request body")
            return@post
        }

        when {
            request.email.isBlank() || request.password.isBlank() -> {
                call.respond(HttpStatusCode.BadRequest, "Username and password cannot be blank")
                return@post
            }
            request.password.length < 8 -> {
                call.respond(HttpStatusCode.BadRequest, "Password must be at least 8 characters")
                return@post
            }
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)
        val user = User(
            username = request.email,
            password = saltedHash.hash,
            salt = saltedHash.salt
        )
        val wasAcknowledged = userDataSource.insertUser(user)
        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict, "Username already exists")
            return@post
        }
        call.respond(HttpStatusCode.OK, "User created successfully")
    }
}

fun Route.signIn(
    hashingService: HashingService,
    userDataSource: UserDataSource,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    post("signin") {
        val request = call.receiveNullable<AuthRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest, "Invalid request body")
            return@post
        }

        val user = userDataSource.getUserByUsername(request.email)
        if (user == null || !hashingService.verify(
                value = request.password,
                saltedHash = SaltedHash(
                    hash = user.password,
                    salt = user.salt
                )
            )
        ) {
            call.respond(HttpStatusCode.Unauthorized, "Incorrect username or password")
            return@post
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = user.id
            )
        )

        call.respond(
            status = HttpStatusCode.OK,
            message = AuthResponse(
                token = token
            )
        )
    }
}

fun Route.authenticate() {
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.getSecretInfo() {
    authenticate {
        get("secret") {
            val principal = call.principal<JWTPrincipal>()
            val userId = principal?.getClaim("userId", String::class)
            if (userId == null) {
                call.respond(HttpStatusCode.Unauthorized, "Invalid or missing token")
                return@get
            }
            call.respond(HttpStatusCode.OK, "Your userId is $userId")
        }
    }
}