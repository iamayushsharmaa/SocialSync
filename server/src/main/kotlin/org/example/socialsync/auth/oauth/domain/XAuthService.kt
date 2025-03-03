package org.example.socialsync.auth.oauth.domain

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.socialsync.auth.oauth.data.XConfig
import org.example.socialsync.auth.oauth.repository.XTokenRepository

class XAuthService (
    private val client: HttpClient,
    private val tokenRepository: XTokenRepository
){

    fun getAuthUrl(state: String, codeChallenge: String): String {
        return XConfig.TWITTER_AUTH_URL +
                "?response_type=code" +
                "&client_id=${XConfig.TWITTER_CLIENT_ID}" +
                "&redirect_uri=${XConfig.TWITTER_REDIRECT_URI}" +
                "&scope=tweet.write%20tweet.read%20users.read" +
                "&state=$state" +
                "&code_challenge=$codeChallenge" +
                "&code_challenge_method=plain"
    }


    suspend fun exchangeCodeForToken(code: String, codeVerifier: String): String {
        val response = client.post(XConfig.TWITTER_TOKEN_URL) {
            setBody(FormDataContent(Parameters.build {
                append("grant_type", "authorization_code")
                append("code", code)
                append("client_id", XConfig.TWITTER_CLIENT_ID)
                append("client_secret", XConfig.TWITTER_CLIENT_SECRET)
                append("redirect_uri", XConfig.TWITTER_REDIRECT_URI)
                append("code_verifier", codeVerifier)
            }))
        }.bodyAsText()

        val json = Json.parseToJsonElement(response).jsonObject
        val accessToken = json["access_token"]?.jsonPrimitive?.content
            ?: throw Exception("Failed to get access token")
        return accessToken
    }

    suspend fun postTweet(userId: String, message: String) {
        val token = tokenRepository.getToken(userId) ?: throw Exception("No token for user")
        client.post("${XConfig.TWITTER_API_BASE}/tweets") {
            header("Authorization", "Bearer $token")
            setBody("""{"text": "$message"}""")
            contentType(ContentType.Application.Json)
        }
    }
}