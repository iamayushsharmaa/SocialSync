package org.example.socialsync.data.posts.data.response

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialPlatform

@Serializable
data class PostResponse(
    val postId : String,
    val userId: String,
    val text: String?,
    val media: List<String>? = emptyList(),
    val date: LocalDate?,
    val time: LocalTime?,
    val status: PostStatus,
    val createdAt: Long,
    val updatedAt: Long,
    val socials: List<SocialPlatform>,
)
