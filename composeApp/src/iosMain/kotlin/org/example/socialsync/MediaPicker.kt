package org.example.socialsync

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

actual class MediaPicker {
    actual fun launchImagePicker(onResult: (List<String>) -> Unit) {
        onResult(emptyList())
    }
    
    actual fun launchVideoPicker(onResult: (List<String>) -> Unit) {
        onResult(emptyList())
    }
}

@Composable
actual fun rememberMediaPicker(): MediaPicker {
    return remember { MediaPicker() }
}