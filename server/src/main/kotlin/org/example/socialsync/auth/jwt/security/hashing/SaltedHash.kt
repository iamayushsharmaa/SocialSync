package org.example.socialsync.auth.jwt.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)