package org.example.socialsync.plugins


import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.client.HttpClient
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.example.socialsync.auth.oauth.repository.configureGoogleOAuth
import org.example.socialsync.auth.jwt.security.token.TokenConfig

fun Application.configureSecurity(
    config: TokenConfig,
    httpClient: HttpClient,
    redirects: MutableMap<String, String>
) {
    authentication {
        jwt {
            realm = "ktor socialsync app"
            verifier(
                JWT
                    .require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }

        configureGoogleOAuth(httpClient, redirects)

    }
}