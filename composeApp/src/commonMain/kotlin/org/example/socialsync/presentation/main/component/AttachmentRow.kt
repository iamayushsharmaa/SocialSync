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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.socialsync.MediaPicker
import org.example.socialsync.app.App
import org.example.socialsync.app.AppColor
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentRow(
    modifier: Modifier = Modifier,
    mediaPicker: MediaPicker,
    selectedMediaUris: List<String>,
    onTagClick: () -> Unit,
    onImagePicked: (List<String>) -> Unit,
    onVideoPicked: (List<String>) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ActionButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                icon = Resource.Icons.ATTACH_ICON,
                contentDescription = "Pick Image",
                text = "Image",
                onClick = {
                    mediaPicker.launchVideoPicker(onImagePicked)
                }
            )
            Spacer(Modifier.width(12.dp))
            ActionButton(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                icon = Resource.Icons.ATTACH_ICON,
                text = "Video",
                contentDescription = "Pick Video",
                onClick = { mediaPicker.launchVideoPicker(onVideoPicked) }
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
    modifier: Modifier = Modifier,
    text: String,
    icon: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF5F8FA),
            contentColor = AppColor.Black
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 16.sp
            )
        }
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