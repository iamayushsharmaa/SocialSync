package org.example.socialsync.data.auth.api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.socialsync.core.data.safeCall
import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.auth.model.AuthRequest
import org.example.socialsync.data.auth.model.TokenResponse

private val BASE_URL = "http://10.0.0:8080"

class AuthApiService(
    private val httpClient: HttpClient
) {
    suspend fun signUp(request: AuthRequest) : Result<Unit, DataError.Remote> {
        return safeCall {
            httpClient.post(urlString = "$BASE_URL/signup"){
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
    suspend fun signIn(request: AuthRequest) : Result<TokenResponse, DataError.Remote> {
        return safeCall {
            httpClient.post(urlString = "$BASE_URL/signin"){
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }
    }
}