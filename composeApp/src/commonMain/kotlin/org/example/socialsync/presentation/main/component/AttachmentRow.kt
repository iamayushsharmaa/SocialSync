package org.example.socialsync.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.socialsync.MediaPicker
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentRow(
    modifier: Modifier = Modifier,
    onTagClick: () -> Unit,
    mediaPicker: MediaPicker,
    selectedMediaUris: List<String> = emptyList(),
    onMediaPicked: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AppColor.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton(
                icon = Resource.Icons.ATTACH_ICON,
                contentDescription = "Pick Image",
                onClick = {
                    mediaPicker.launchImagePicker { uris -> onMediaPicked() }
                }
            )
            ActionButton(
                icon = Resource.Icons.ATTACH_ICON,
                contentDescription = "Pick Video",
                onClick = { mediaPicker.launchVideoPicker { uris -> onMediaPicked() } }
            )
            ActionButton(
                icon = Resource.Icons.ATTACH_ICON,
                contentDescription = "Add Tag",
                onClick = { onTagClick() }
            )
        }

        AnimatedVisibility(
            visible = selectedMediaUris.isNotEmpty(),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            MediaPager(
                uris = selectedMediaUris,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
private fun ActionButton(
    icon: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(38.dp)
            .background(
                color = AppColor.WhiteFade,
                shape = CircleShape
            )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = AppColor.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun MediaPager(
    uris: List<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { uris.size })

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp), // Fixed height for both image and video
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val uri = uris[page]
            val isVideo = uri.toString().endsWith(".mp4") || uri.toString().endsWith(".mov")

            if (isVideo) {
                VideoPreview(uri)
            } else {
                ImagePreview(uri)
            }
        }
    }
}

@Composable
fun ImagePreview(uri: String) {
    AsyncImage(
        model = uri,
        contentDescription = "Selected Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun VideoPreview(uri: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Resource.Icons.EYE_CLOSED),
            contentDescription = "Video Preview",
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
    }
}