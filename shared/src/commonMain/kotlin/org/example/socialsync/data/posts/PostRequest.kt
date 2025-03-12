package org.example.socialsync.data.posts

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val postId : String,
    val text: String?,
    val media: List<String>?,
    val date: LocalDate?,
    val time: LocalTime?,
    val socials: List<Socials>,
)

enum class Socials{
    INSTAGRAM,
    X
}