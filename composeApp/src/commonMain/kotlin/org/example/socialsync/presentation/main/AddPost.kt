package org.example.socialsync.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.example.socialsync.MediaPicker
import org.example.socialsync.app.AppColor
import org.example.socialsync.presentation.main.component.AttachmentRow
import org.example.socialsync.presentation.main.component.TextInput
import org.example.socialsync.presentation.main.component.showDatePicker
import org.example.socialsync.presentation.main.component.showTimePicker
import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import socialsync.composeapp.generated.resources.Res
import socialsync.composeapp.generated.resources.choose_one
import socialsync.composeapp.generated.resources.draft
import socialsync.composeapp.generated.resources.post_now
import socialsync.composeapp.generated.resources.schedule

@Composable
fun AddPost(
    navController: NavHostController,
    onTagClick: () -> Unit,
    onImagePick: (List<String>) -> Unit,
    onVideoPick: (List<String>) -> Unit,
    mediaPicker: MediaPicker,
    selectedMediaUris: List<String>,
) {

    val showBottomSheet = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(AppColor.White)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = CenterVertically
        ){
            Icon(
                painter = painterResource(Resource.Icons.BACK_ICON),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 12.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(Resource.Icons.GOOGLE),
                contentDescription = "",
                modifier = Modifier
                    .size(33.dp)
            )
            Spacer(Modifier.weight(1f))
        }

        Spacer(Modifier.height(10.dp))
        Text(
            text = "Text",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 18.dp)
        )
        TextInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        Spacer(Modifier.height(10.dp))
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
            selectedMediaUris = selectedMediaUris,
            mediaPicker = mediaPicker,
            onImagePicked = { uris->
                onImagePick(uris)
            },
            onVideoPicked = { uris->
                onVideoPick(uris)
            }
        )
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                showBottomSheet.value = true
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp, bottom = 22.dp)
                .size(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppColor.Black
            ),
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
                onDismiss = { showBottomSheet.value = false }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostOptionsBottomSheet(onDismiss: () -> Unit) {

    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showDateTime by remember { mutableStateOf(false) }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showDatePicker){
        showDatePicker(
            showDatePicker,
            onDateSelected = { date->
                selectedDate = date
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            },
        )
    }
    if (showTimePicker){
        showTimePicker(
            showTimePicker,
            onTimeSelected = { time->
                selectedTime = time
                showTimePicker = false
            },
            onDismiss = {
                showTimePicker = false
            },
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
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
                    .padding(8.dp)
                    .background(shape = RoundedCornerShape(12.dp), color = AppColor.White),
                text = stringResource(Res.string.schedule),
                icon = Resource.Icons.DATETIME_ICON,
                isSelected = selectedOption == "Schedule",
                onClick = {
                    selectedOption = "Schedule"
                    showDateTime = true
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
                            text = selectedDate?.toString() ?: "Pick Date",
                            icon = Resource.Icons.DATETIME_ICON,
                            onClick = {
                               showDatePicker = true
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
                            text = selectedTime?.toString() ?: "Pick Time",
                            icon = Resource.Icons.DATETIME_ICON,
                            onClick = {
                                showTimePicker = true
                            }
                        )
                    }
                }
            }
            PostOption(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(8.dp)
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
                    .padding(8.dp)
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