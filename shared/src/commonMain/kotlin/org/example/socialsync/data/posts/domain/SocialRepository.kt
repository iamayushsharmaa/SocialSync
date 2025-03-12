package org.example.socialsync.data.posts.domain

import org.example.socialsync.core.domain.DataError
import org.example.socialsync.core.domain.Result
import org.example.socialsync.data.posts.data.response.PostResponse
import org.example.socialsync.data.posts.data.request.PostRequest
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialAccount
import org.example.socialsync.data.posts.data.request.SocialPlatform

interface SocialMediaRepository {
    suspend fun getSocialAccounts(userId: String): Result<List<SocialAccount>, DataError.Remote>
    suspend fun createPost(post: PostRequest): Result<PostRequest, DataError.Remote>
    suspend fun updatePost(post: PostRequest): Result<PostRequest, DataError.Remote>
    suspend fun getPosts(userId: String, status: PostStatus?): Result<PostResponse, DataError.Remote>
    suspend fun publishPostNow(postId: String, platforms: List<SocialPlatform>) : Result<Unit, DataError.Remote>
    suspend fun saveDraft(post: PostRequest): PostRequest
}