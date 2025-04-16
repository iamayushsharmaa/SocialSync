package org.example.socialsync.data.posts.data.request

import com.mohamedrejeb.calf.io.KmpFile
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val postId : String,
    val userId: String,
    val text: String?,
    val media: List<@Contextual KmpFile>? = emptyList(),
    val date: LocalDate?,
    val time: LocalTime?,
    val status: PostStatus,
    val createdAt: Long,
    val updatedAt: Long,
    val socials: List<SocialPlatform>
)
