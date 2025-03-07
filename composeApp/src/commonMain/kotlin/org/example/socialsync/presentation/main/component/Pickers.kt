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
//    DatePickerDialog(
//        LocalContext.current,
//        { _, year, month, day ->
//            onDateSelected(LocalDate(year, month + 1, day))
//        },
//        now.year,
//        now.monthNumber - 1,
//        now.dayOfMonth
//    ).show()
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