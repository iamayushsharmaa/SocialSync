package org.example.socialsync.auth.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)