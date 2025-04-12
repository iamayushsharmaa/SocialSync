package org.example.socialsync.data.posts.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.socialsync.core.data.safeCall
import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.posts.data.response.PostResponse


private val BASE_URL = "http://10.0.0:8080"

class SocialApi (
    private val httpClient: HttpClient
) {
    suspend fun getPosts(): Result<List<PostResponse>, DataError.Remote> {
        return safeCall {
            httpClient.get(urlString = "$BASE_URL/posts") {
                contentType(ContentType.Application.Json)
            }
        }
    }
}