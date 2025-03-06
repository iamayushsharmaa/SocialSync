package org.example.socialsync

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

actual class MediaPicker(
    private val imageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, List<Uri>>,
    private val videoLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, List<Uri>>
) {
    actual fun launchImagePicker(onResult: (List<String>) -> Unit) {
        imageLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
            onResult = { uris -> onResult(uris.map { it.toString() }) }
        )
    }

    actual fun launchVideoPicker(onResult: (List<String>) -> Unit) {
        videoLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly),
            onResult = { uris -> onResult(uris.map { it.toString() }) }
        )
    }
}

@Composable
actual fun rememberMediaPicker(): MediaPicker {
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { }

    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) {  }

    return remember { MediaPicker(imageLauncher, videoLauncher) }
}

// Extension function to handle the callback properly
fun <I, O> ManagedActivityResultLauncher<I, O>.launch(
    input: I,
    onResult: (O) -> Unit
) {
    launch(input) { result ->
        onResult(result)
    }
}