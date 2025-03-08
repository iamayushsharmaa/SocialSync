package org.example.socialsync.presentation.main.component

import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import network.chaintech.cmpimagepickncrop.CMPImagePickNCropDialog
import network.chaintech.cmpimagepickncrop.imagecropper.ImageCropper

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    imageCropper: ImageCropper,
    openImagePicker: Boolean,
    onOpenImagePickerChange: (Boolean) -> Unit,
    onSelectedImage: (ImageBitmap) -> Unit
) {
    CMPImagePickNCropDialog(
        imageCropper = imageCropper,
        cropEnable = true,
        openImagePicker = openImagePicker,
        showGalleryOption = true,
        showCameraOption = true,
        imagePickerDialogHandler = {
            onOpenImagePickerChange(it)
        },
        selectedImageCallback = { image->
            onSelectedImage(image)
        }
    )
}
