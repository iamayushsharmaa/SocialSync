package org.example.socialsync.data.posts.data

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val postId : String,
    val text: String?,
    val media: List<String>? = emptyList(),
    val date: LocalDate?,
    val time: LocalTime?,
    val status: PostStatus,
    val createdAt: Long,
    val updatedAt: Long,
    val socials: List<SocialPlatform>,
)
