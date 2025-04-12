package org.example.socialsync.data.posts.domain

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
        TODO("Not yet implemented")
    }

    override suspend fun createPost(post: PostRequest): Result<PostRequest, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePost(post: PostRequest): Result<PostRequest, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(
        userId: String,
        status: PostStatus?
    ): Result<PostResponse, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun publishPostNow(
        postId: String,
        platforms: List<SocialPlatform>
    ): Result<Unit, DataError.Remote> {
        TODO("Not yet implemented")
    }

    override suspend fun saveDraft(post: PostRequest): PostRequest {
        TODO("Not yet implemented")
    }
}