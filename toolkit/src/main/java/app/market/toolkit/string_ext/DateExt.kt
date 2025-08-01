package app.market.toolkit.string_ext

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatDateToRussian(inputDate: String): String {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date: Date = inputFormat.parse(inputDate) ?: return ""

    val monthsInRussian = listOf(
        "января", "февраля", "марта", "апреля", "мая", "июня",
        "июля", "августа", "сентября", "октября", "ноября", "декабря"
    )

    val calendar = Calendar.getInstance().apply { time = date }
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)

    return "Опубликовано $day ${monthsInRussian[month]}"
}

fun formatToRussianDate(inputDate: String): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(inputDate, formatter)

        val russianFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
        return date.format(russianFormatter)
    } else {
        return inputDate
    }
}