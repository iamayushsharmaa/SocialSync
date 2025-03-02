package org.example.socialsync.auth.jwt.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse (
    val token: String
)