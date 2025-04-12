package org.example.socialsync.data.posts.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.example.socialsync.data.posts.data.request.PostRequest
import org.example.socialsync.data.posts.data.state.PostEffect
import org.example.socialsync.data.posts.data.state.PostIntent
import org.example.socialsync.data.posts.data.state.PostState
import org.example.socialsync.data.posts.domain.SocialMediaRepository

class PostViewModel(
    private val repository: SocialMediaRepository,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<PostEffect>()
    val effect = _effect.asSharedFlow()

    fun handleIntent(intent: PostIntent) {
        when (intent) {
            is PostIntent.UpdatePostId -> updateState { copy(postId = intent.postId) }
            is PostIntent.UpdateUserId -> updateState { copy(userId = intent.userId) }
            is PostIntent.UpdateText -> updateState { copy(text = intent.text) }
            is PostIntent.AddMedia -> updateState { copy(media = media + intent.mediaUrl) }
            is PostIntent.RemoveMedia -> updateState { copy(media = media - intent.mediaUrl) }
            is PostIntent.UpdateDate -> updateState { copy(date = intent.date) }
            is PostIntent.UpdateTime -> updateState { copy(time = intent.time) }
            is PostIntent.UpdateStatus -> updateState { copy(status = intent.status) }
            is PostIntent.AddSocial -> updateState { copy(socials = socials + intent.social) }
            is PostIntent.RemoveSocial -> updateState { copy(socials = socials - intent.social) }
            is PostIntent.SubmitPost -> submitPost()
        }
    }

    private fun updateState(update: PostState.() -> PostState) {
        _state.value = _state.value.update()
    }

    private fun submitPost() {
        val currentState = _state.value
        if (currentState.postId.isBlank() || currentState.userId.isBlank()) {
            coroutineScope.launch {
                _effect.emit(PostEffect.ShowError("Post ID and User ID are required"))
            }
            return
        }

        updateState { copy(isLoading = true, error = null) }

        coroutineScope.launch {
            val postRequest = PostRequest(
                postId = currentState.postId,
                userId = currentState.userId,
                text = currentState.text.takeIf { it.isNotBlank() },
                media = currentState.media,
                date = currentState.date,
                time = currentState.time,
                status = currentState.status,
                createdAt = Clock.System.now().toEpochMilliseconds(),
                updatedAt = Clock.System.now().toEpochMilliseconds(),
                socials = currentState.socials
            )

            val result = repository.createPost(postRequest)
            updateState { copy(isLoading = false) }

            result.fold(
                onSuccess = {
                    updateState { copy(isPostSubmitted = true) }
                    _effect.emit(PostEffect.PostSubmittedSuccessfully)
                },
                onFailure = { error ->
                    updateState { copy(error = error.toString()) }
                    _effect.emit(PostEffect.ShowError(error.toString()))
                }
            )
        }
    }
}