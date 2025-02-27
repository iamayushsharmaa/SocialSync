package org.example.socialsync.auth.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse (
    val token: String
)