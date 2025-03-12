package org.example.socialsync.data.posts.data

data class SocialAccount(
    val id: String,
    val userId: String,
    val platform: SocialPlatform,
    val accountHandle: String,
    val accessToken: String,
    val refreshToken: String? = null
)