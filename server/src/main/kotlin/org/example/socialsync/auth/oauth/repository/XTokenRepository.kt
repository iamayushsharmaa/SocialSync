package org.example.socialsync.auth.oauth.repository

class XTokenRepository {
    // In-memory storage (replace with database in production)
    private val tokenStore = mutableMapOf<String, String>() // userId -> accessToken

    fun saveToken(userId: String, token: String) {
        tokenStore[userId] = token
    }

    fun getToken(userId: String): String? {
        return tokenStore[userId]
    }
}