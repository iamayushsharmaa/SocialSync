// composeApp/src/desktopMain/kotlin/com/example/socialmediaapp/MediaPicker.kt
package org.example.socialsync

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

actual class MediaPicker {
    actual fun launchImagePicker(onResult: (List<String>) -> Unit) {
        // Stub for desktop; implement desktop-specific picker if needed
        onResult(emptyList())
    }
    
    actual fun launchVideoPicker(onResult: (List<String>) -> Unit) {
        // Stub for desktop; implement desktop-specific picker if needed
        onResult(emptyList())
    }
}

@Composable
actual fun rememberMediaPicker(): MediaPicker {
    return remember { MediaPicker() }
}