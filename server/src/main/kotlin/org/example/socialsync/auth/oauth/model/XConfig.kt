package org.example.socialsync.auth.oauth.model

object XConfig {
    const val TWITTER_CLIENT_ID = "your_client_id"
    const val TWITTER_CLIENT_SECRET = "your_client_secret"
    const val TWITTER_REDIRECT_URI = "http://localhost:8080/auth/twitter/callback"
    const val TWITTER_AUTH_URL = "https://twitter.com/i/oauth2/authorize"
    const val TWITTER_TOKEN_URL = "https://api.twitter.com/2/oauth2/token"
    const val TWITTER_API_BASE = "https://api.twitter.com/2"
}