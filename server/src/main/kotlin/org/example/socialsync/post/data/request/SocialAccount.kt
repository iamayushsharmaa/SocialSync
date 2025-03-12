package org.example.socialsync.post.data.request

data class SocialAccount(
    val id: String,
    val userId: String,
    val platform: SocialPlatform,
    val accountHandle: String,
    val accessToken: String,
    val refreshToken: String? = null
)