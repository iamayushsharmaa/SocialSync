package org.example.socialsync.data.posts.data.state

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.example.socialsync.data.posts.data.request.PostStatus
import org.example.socialsync.data.posts.data.request.SocialPlatform

data class PostState(
    val postId: String = "",
    val userId: String = "",
    val text: String = "",
    val media: List<String> = emptyList(),
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val status: PostStatus = PostStatus.DRAFT,
    val socials: List<SocialPlatform> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isPostSubmitted: Boolean = false,
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val showDateTime: Boolean = false
)

sealed class PostIntent {
    data class UpdatePostId(val postId: String) : PostIntent()
    data class UpdateUserId(val userId: String) : PostIntent()
    data class UpdateText(val text: String) : PostIntent()
    data class AddMedia(val mediaUrl: List<String>) : PostIntent()
    data class RemoveMedia(val mediaUrl: String) : PostIntent()
    data class UpdateDate(val date: LocalDate?) : PostIntent()
    data class UpdateTime(val time: LocalTime?) : PostIntent()
    data class UpdateStatus(val status: PostStatus) : PostIntent()
    data class AddSocial(val social: SocialPlatform) : PostIntent()
    data class RemoveSocial(val social: SocialPlatform) : PostIntent()
    data class ShowDatePicker(val showDatePicker: Boolean) : PostIntent()
    data class ShowTimePicker(val showTimePicker: Boolean) : PostIntent()
    data class ShowDateTime(val showDateTime: Boolean) : PostIntent()
    object SubmitPost : PostIntent()
}

sealed class PostEffect {
    object PostSubmittedSuccessfully : PostEffect()
    data class ShowError(val message: String) : PostEffect()
}