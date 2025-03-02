package org.example.socialsync.auth.oauth.model

import kotlinx.serialization.Serializable

@Serializable
data class GoogleUserResponse(
    val email: String,
    val name: String
)