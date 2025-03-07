package org.example.socialsync.presentation.main.component

import androidx.compose.material3.DatePickerDialog
import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun showDatePicker(onDateSelected: (LocalDate) -> Unit) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

}

@Composable
fun showTimePicker(onTimeSelected: (LocalTime) -> Unit) {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
//    TimePickerDialog(
//        LocalContext.current,
//        { _, hour, minute ->
//            onTimeSelected(LocalTime(hour, minute))
//        },
//        now.hour,
//        now.minute,
//        true // 24-hour format
//    ).show()
}