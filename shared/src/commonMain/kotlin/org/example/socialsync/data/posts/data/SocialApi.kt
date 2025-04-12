package org.example.socialsync.data.posts.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.socialsync.core.data.safeCall
import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.posts.data.request.PostRequest
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialAccount
import org.example.socialsync.data.posts.data.request.SocialPlatform
import org.example.socialsync.data.posts.data.response.PostResponse


private val baseUrl = "http://10.0.0:8080"


interface SocialApi {
    suspend fun getSocialAccounts(userId: String): List<SocialAccount>
    suspend fun createPost(post: PostRequest): PostRequest
    suspend fun updatePost(post: PostRequest): PostRequest
    suspend fun getPosts(userId: String, status: PostStatus? = null): PostResponse
    suspend fun publishPostNow(postId: String, platforms: List<SocialPlatform>): Unit
    suspend fun saveDraft(post: PostRequest): PostRequest
    suspend fun deletePost(postId: String): Unit
}

class SocialApiImpl (
    private val httpClient: HttpClient
): SocialApi {

    override suspend fun getSocialAccounts(userId: String): List<SocialAccount> {
        return httpClient.get("$baseUrl/socialaccounts") {
            parameter("userId", userId)
        }.body()
    }

    override suspend fun createPost(post: PostRequest): PostRequest {
        return httpClient.post("$baseUrl/posts") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }.body()
    }

    override suspend fun updatePost(post: PostRequest): PostRequest {
        return httpClient.put("$baseUrl/posts/${post.postId}") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }.body()
    }

    override suspend fun getPosts(userId: String, status: PostStatus?): PostResponse {
        return httpClient.get("$baseUrl/posts") {
            parameter("userId", userId)
            status?.let { parameter("status", it.name) }
        }.body()
    }

    override suspend fun publishPostNow(postId: String, platforms: List<SocialPlatform>): Unit {
        httpClient.post("$baseUrl/posts/$postId/publish") {
            contentType(ContentType.Application.Json)
            setBody(platforms)
        }
    }

    override suspend fun saveDraft(post: PostRequest): PostRequest {
        return httpClient.post("$baseUrl/posts/draft") {
            contentType(ContentType.Application.Json)
            setBody(post)
        }.body()
    }

    override suspend fun deletePost(postId: String): Unit {
        httpClient.delete("$baseUrl/posts/$postId")
    }
}