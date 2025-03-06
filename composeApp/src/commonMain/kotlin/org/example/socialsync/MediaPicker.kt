package org.example.socialsync

import androidx.compose.runtime.Composable

expect class MediaPicker {
    fun launchImagePicker(onResult: (List<String>) -> Unit)
    fun launchVideoPicker(onResult: (List<String>) -> Unit)
}

@Composable
expect fun rememberMediaPicker(): MediaPicker