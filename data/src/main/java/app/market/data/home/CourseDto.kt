package app.market.data.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseDto(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val price: String = "",
    val rate: String = "",
    val startDate: String = "",
    var hasLike: Boolean = false,
    val publishDate: String = "",
): Parcelable