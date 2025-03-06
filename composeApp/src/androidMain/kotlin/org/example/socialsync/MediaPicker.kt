package org.example.socialsync

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class MediaPicker(
    private val imageLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, List<Uri>>,
    private val videoLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, List<Uri>>
) {
    actual fun launchImagePicker(onResult: (List<String>) -> Unit) {
        imageLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    actual fun launchVideoPicker(onResult: (List<String>) -> Unit) {
        videoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
    }
}

@Composable
actual fun rememberMediaPicker(): MediaPicker {
    val imageLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        onResult(uris.map { it.toString() })
    }
    val videoLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()
    ) { uris ->
        onResult(uris.map { it.toString() })
    }
    return remember { MediaPicker(imageLauncher, videoLauncher) }
}