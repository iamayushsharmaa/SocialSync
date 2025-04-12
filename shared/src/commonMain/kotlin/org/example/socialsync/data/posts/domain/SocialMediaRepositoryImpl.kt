package org.example.socialsync.data.posts.domain

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.posts.data.SocialApi
import org.example.socialsync.data.posts.data.request.PostRequest
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialAccount
import org.example.socialsync.data.posts.data.request.SocialPlatform
import org.example.socialsync.data.posts.data.response.PostResponse

class SocialMediaRepositoryImpl (
    private val socialApi: SocialApi
) : SocialMediaRepository {
    override suspend fun getSocialAccounts(userId: String): Result<List<SocialAccount>, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                val accounts = socialApi.getSocialAccounts(userId)
                Result.Success(accounts)
            } catch (e: ClientRequestException) {
                when (e.response.status) {
                    HttpStatusCode.NotFound -> Result.Error(DataError.Remote.UNKNOWN)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            } catch (e: RuntimeException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
    }

    override suspend fun createPost(post: PostRequest): Result<PostRequest, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                val createdPost = socialApi.createPost(post)
                Result.Success(createdPost)
            } catch (e: ClientRequestException) {
                Result.Error(DataError.Remote.UNKNOWN)
            } catch (e: HttpRequestTimeoutException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
    }

    override suspend fun updatePost(post: PostRequest): Result<PostRequest, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                val updatedPost = socialApi.updatePost(post)
                Result.Success(updatedPost)
            } catch (e: ClientRequestException) {
                when (e.response.status) {
                    HttpStatusCode.NotFound -> Result.Error(DataError.Remote.UNKNOWN)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            } catch (e: HttpRequestTimeoutException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
    }

    override suspend fun getPosts(
        userId: String,
        status: PostStatus?
    ): Result<PostResponse, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                val response = socialApi.getPosts(userId, status)
                Result.Success(response)
            } catch (e: ClientRequestException) {
                when (e.response.status) {
                    HttpStatusCode.NotFound -> Result.Error(DataError.Remote.UNKNOWN)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            } catch (e: HttpRequestTimeoutException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }    }

    override suspend fun publishPostNow(
        postId: String,
        platforms: List<SocialPlatform>
    ): Result<Unit, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                socialApi.publishPostNow(postId, platforms)
                Result.Success(Unit)
            } catch (e: ClientRequestException) {
                when (e.response.status) {
                    HttpStatusCode.NotFound -> Result.Error(DataError.Remote.NO_INTERNET)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            } catch (e: HttpRequestTimeoutException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
    }

    override suspend fun deletePost(postId: String): Result<Unit, DataError.Remote> {
        return withContext(Dispatchers.Default) {
            try {
                socialApi.deletePost(postId)
                Result.Success(Unit)
            } catch (e: ClientRequestException) {
                when (e.response.status) {
                    HttpStatusCode.NotFound -> Result.Error(DataError.Remote.NO_INTERNET)
                    else -> Result.Error(DataError.Remote.UNKNOWN)
                }
            } catch (e: HttpRequestTimeoutException) {
                Result.Error(DataError.Remote.NO_INTERNET)
            } catch (e: Exception) {
                Result.Error(DataError.Remote.UNKNOWN)
            }
        }
    }

    override suspend fun saveDraft(post: PostRequest): PostRequest {
        return withContext(Dispatchers.Default) {
            try {
                socialApi.saveDraft(post)
            } catch (e: Exception) {
                throw IllegalStateException("Failed to save draft: ${e.message}")
            }
        }
    }
}