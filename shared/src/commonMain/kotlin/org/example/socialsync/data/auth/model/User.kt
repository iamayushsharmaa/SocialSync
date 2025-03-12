package org.example.socialsync.data.auth.model

data class User(
    val id: String,
    val email: String,
    val password: String,
    val fullName: String? = null,
    val profilePictureUrl: String? = null
)
