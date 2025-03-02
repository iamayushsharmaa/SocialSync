package org.example.socialsync.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String
)