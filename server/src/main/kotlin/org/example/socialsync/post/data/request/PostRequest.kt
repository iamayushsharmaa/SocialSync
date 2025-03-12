package org.example.socialsync.post.data.request


import kotlinx.serialization.Serializable
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialPlatform
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class PostRequest(
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
