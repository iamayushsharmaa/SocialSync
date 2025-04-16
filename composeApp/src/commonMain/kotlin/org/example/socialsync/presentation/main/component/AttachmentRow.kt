package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.mohamedrejeb.calf.io.KmpFile
import org.example.socialsync.app.AppColor
import org.example.socialsync.data.posts.presentation.PostViewModel
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AttachmentRow(
    modifier: Modifier = Modifier,
    onTagClick: () -> Unit,
    onImageClick: () -> Unit,
    onVideoClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
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
                    onImageClick()
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
                onClick = {
                    onVideoClick()
                }
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
fun MediaLayout(
    uris: List<KmpFile>,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    postViewModel: PostViewModel
) {

    val state by postViewModel.state.collectAsState()

    LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(14.dp)
        ) {
            itemsIndexed(uris) { _, uri ->
                val isVideo = uri.toString().endsWith(".mp4") || uri.toString().endsWith(".mov")
                Card(
                    modifier = Modifier
                        .width(360.dp)
                        .height(250.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    if (isVideo) {
                        VideoPreview(
                            modifier = Modifier.fillMaxSize(),
                            uri = uri,
                            imageLoader = imageLoader
                        )
                    } else {
                        ImagePreview(
                            modifier = Modifier.fillMaxSize(),
                            uri = uri,
                            imageLoader = imageLoader
                        )
                    }
                }
            }

    }
}

@Composable
fun ImagePreview(
    modifier: Modifier = Modifier,
    uri: KmpFile,
    imageLoader: ImageLoader
) {
    AsyncImage(
        modifier = modifier,
        model = uri,
        imageLoader = imageLoader,
        contentDescription = "Image Preview",
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun VideoPreview(
    modifier: Modifier = Modifier,
    uri: KmpFile,
    imageLoader: ImageLoader
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "Video Preview",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader
        )
        Icon(
            painter = painterResource(Resource.Icons.ADD_BLANK),
            contentDescription = "Play",
            tint = Color.Black,
            modifier = Modifier.size(70.dp)
        )
    }
}

