package org.example.socialsync.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.ImageLoader
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.io.getPath
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.coil.KmpFileFetcher
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.example.socialsync.app.AppColor
import org.example.socialsync.app.PermissionsViewModel
import org.example.socialsync.data.auth.presentaion.AuthViewModel
import org.example.socialsync.data.posts.data.state.PostIntent
import org.example.socialsync.data.posts.presentation.PostViewModel
import org.example.socialsync.presentation.main.component.AttachmentRow
import org.example.socialsync.presentation.main.component.MediaLayout
import org.example.socialsync.presentation.main.component.SocialsDesign
import org.example.socialsync.presentation.main.component.TextInput
import org.example.socialsync.presentation.main.component.showDatePicker
import org.example.socialsync.presentation.main.component.showTimePicker
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.viewmodel.getViewModelKey
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.choose_one
import socialsync.composeapp.generated.resources.draft
import socialsync.composeapp.generated.resources.post_now
import socialsync.composeapp.generated.resources.schedule

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPost(
    navController: NavHostController,
    onTagClick: () -> Unit,
    permissionViewModel: PermissionsViewModel
) {

    val postViewModel = koinViewModel<PostViewModel>()
    val state by postViewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current

    val imagePickerLauncher = rememberFilePickerLauncher(
        type = FilePickerFileType.Image,
        selectionMode = FilePickerSelectionMode.Multiple,
        onResult = { uris ->
            scope.launch {
                val media = uris
                postViewModel.handleIntent(PostIntent.AddMedia(media))
            }
        }
    )
    val videoPickerLauncher = rememberFilePickerLauncher(
        type = FilePickerFileType.Video,
        selectionMode = FilePickerSelectionMode.Multiple,
        onResult = { uris ->
            scope.launch {
                val media = uris
                postViewModel.handleIntent(PostIntent.AddMedia(media))
            }
        }
    )

    val imageLoader = ImageLoader.Builder(coil3.compose.LocalPlatformContext.current)
        .components { add(KmpFileFetcher.Factory()) }
        .build()

    val showBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Box(modifier = Modifier.fillMaxSize()){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(AppColor.White).verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Add post",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = AppColor.Black
                )

            }
            Spacer(Modifier.height(6.dp))

            Text(
                text = "Text",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 18.dp)
            )
            TextInput(
                text = state.text,
                onValueChange = { postViewModel.handleIntent(PostIntent.UpdateText(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            )
            AttachmentRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .background(
                        shape = RoundedCornerShape(16.dp),
                        color = AppColor.White
                    ),
                onTagClick = { onTagClick() },
                onImageClick = {
                    imagePickerLauncher.launch()
                },
                onVideoClick = {
                    videoPickerLauncher.launch()
                }
            )

            if (state.media.isNotEmpty()) {
                Text(
                    text = "Selected Media (${state.media.size})",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 20.dp)
                )
                Text(
                    text = "Slide to see more.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(top = 6.dp, start = 20.dp)
                )
                MediaLayout(
                    uris = state.media,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .padding(top = 16.dp)
                        .padding(horizontal = 20.dp),
                    imageLoader = imageLoader,
                    postViewModel
                )
            }
            Spacer(Modifier.weight(1f))
        }
        Button(
            onClick = { showBottomSheet.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 22.dp, bottom = 22.dp)
                .size(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppColor.Black),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(Resource.Icons.SEND_ICON),
                contentDescription = "Add",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
        if (showBottomSheet.value) {
            PostOptionsBottomSheet(
                onDateSelected = { postViewModel.handleIntent(PostIntent.UpdateDate(it))},
                onTimeSelected = { postViewModel.handleIntent(PostIntent.UpdateTime(it))},
                sheetState,
                onDismiss = { showBottomSheet.value = false },
                postViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostOptionsBottomSheet(
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    postViewModel: PostViewModel
) {

    val state by postViewModel.state.collectAsState()
    var selectedOption by remember { mutableStateOf<String?>(null) }

    if (state.showDatePicker){
        showDatePicker(
            state.showDatePicker,
            onDateSelected = { date->
                onDateSelected(date)
                postViewModel.handleIntent(PostIntent.ShowDatePicker(false))
            },
            onDismiss = {
                postViewModel.handleIntent(PostIntent.ShowDatePicker(false))
            },
        )
    }
    if (state.showTimePicker){
        showTimePicker(
            state.showTimePicker,
            onTimeSelected = { time->
                onTimeSelected(time)
                postViewModel.handleIntent(PostIntent.ShowTimePicker(false))

            },
            onDismiss = {
                postViewModel.handleIntent(PostIntent.ShowTimePicker(false))
            },
        )
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AppColor.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = CenterVertically
            ){
                Icon(
                    painter = painterResource(Resource.Icons.BACK_ARRAOW),
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp),
                    tint = AppColor.Black
                )
                Text(
                    text = stringResource(Res.string.choose_one),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AppColor.Black,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Spacer(Modifier.height(16.dp))

            PostOption(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = AppColor.White),
                text = stringResource(Res.string.schedule),
                icon = Resource.Icons.DATETIME_ICON,
                isSelected = selectedOption == "Schedule",
                onClick = {
                    selectedOption = "Schedule"
                    postViewModel.handleIntent(PostIntent.ShowDateTime(true))

                }
            )
            if(selectedOption == "Schedule"){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 18.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ){
                        Text(
                            text = "Date",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = AppColor.Black
                        )
                        DateTimeLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 6.dp)
                                .background(shape = RoundedCornerShape(12.dp), color = AppColor.WhiteFade),
                            text = state.date?.toString() ?: "Pick Date",
                            icon = Resource.Icons.DATETIME_ICON,
                            onClick = {
                                postViewModel.handleIntent(PostIntent.ShowDatePicker(true))

                            }
                        )
                    }
                    Spacer(Modifier.width(9.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ){
                        Text(
                            text = "Time",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = AppColor.Black
                        )
                        DateTimeLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 6.dp)
                                .background(shape = RoundedCornerShape(12.dp), color = AppColor.WhiteFade),
                            text = state.time?.toString() ?: "Pick Time",
                            icon = Resource.Icons.DATETIME_ICON,
                            onClick = {
                                postViewModel.handleIntent(PostIntent.ShowTimePicker(true))
                            }
                        )
                    }
                }
            }
            PostOption(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = AppColor.White),
                text = stringResource(Res.string.post_now),
                icon = Resource.Icons.POST_NOW_ICON,
                isSelected = selectedOption == "Post now",
                onClick = { selectedOption = "Post now" }
            )
            PostOption(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = AppColor.White),
                text = stringResource(Res.string.draft),
                icon = Resource.Icons.DRAFT_ICON,
                isSelected = selectedOption == "Draft",
                onClick = { selectedOption = "Draft" }
            )
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 12.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = AppColor.White,
                    containerColor = AppColor.BlackFade
                ),
                enabled = selectedOption != null ,//&& (selectedOption != "Schedule" || selectedDateTime != null),
            ) {
                Text(
                    text = "Proceed",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColor.White
                )
            }
        }
    }
}

@Composable
private fun PostOption(
    modifier: Modifier = Modifier,
    text: String,
    icon : DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = "icon",
            modifier = Modifier
                .size(24.dp)
                .padding(start = 8.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = AppColor.Black
        )
        Spacer(Modifier.weight(1f))
        RadioButton(
            modifier = Modifier.padding(end = 16.dp),
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = AppColor.Black,
                unselectedColor = AppColor.Gray
            )
        )
    }
}

@Composable
private fun DateTimeLayout(
    modifier: Modifier = Modifier,
    text: String,
    icon: DrawableResource,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick),
        verticalAlignment = CenterVertically
    ) {
        Spacer(
            Modifier.width(8.dp)
        )
        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            modifier = Modifier.size(18.dp),
            tint = AppColor.Black
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = if (text.startsWith("Pick")) Color.Gray else Color.Black
        )
    }
}