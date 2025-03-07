package org.example.socialsync.presentation.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.TimeFormat
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults
import org.example.socialsync.app.AppColor

@Composable
fun showDatePicker(
    showDatePicker: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    WheelDatePickerView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 26.dp),
        showDatePicker = showDatePicker,
        title = "SELECT DATE",
        titleStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AppColor.Black
        ),
        height = 200.dp,
        yearsRange = IntRange(now.year, now.year + 5),
        rowCount = 3,
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        shape = RoundedCornerShape(24.dp),
        selectorProperties = WheelPickerDefaults.selectorProperties(
            borderColor = AppColor.Gray,
        ),
        dateTextColor = AppColor.LIGHTBLUE,
        dateTextStyle = TextStyle(
            color = AppColor.LIGHTBLUE,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        doneLabelStyle = TextStyle(
            color = AppColor.LIGHTBLUE,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        onDoneClick = {
            onDateSelected(it)
        },
        onDismiss = {
            onDismiss()
        }
    )
}

@Composable
fun showTimePicker(
    showTimePicker: Boolean,
    onDismiss: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    WheelTimePickerView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 26.dp),
        showTimePicker = showTimePicker,
        title = "SELECT TIME",
        height = 200.dp,
        rowCount = 3,
        timeFormat = TimeFormat.AM_PM,
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        shape = RoundedCornerShape(24.dp),
        textColor = AppColor.LIGHTBLUE,
        textStyle = TextStyle(
            color = AppColor.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        titleStyle = TextStyle(
            color = AppColor.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        selectorProperties = WheelPickerDefaults.selectorProperties(
            borderColor = AppColor.Gray,
        ),
        doneLabelStyle = TextStyle(
            color = AppColor.LIGHTBLUE,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        onDoneClick = {
            onTimeSelected(it)
        },
        onDismiss = {
            onDismiss()
        }
    )
}