package org.example.socialsync.auth.oauth.data

import kotlinx.serialization.Serializable

@Serializable
data class GoogleUserResponse(
    val email: String,
    val name: String
)