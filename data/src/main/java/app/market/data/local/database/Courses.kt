package app.market.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Courses(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String = "",
    val text: String = "",
    val price: String = "",
    val rate: String = "",
    val startDate: String = "",
    var hasLike: Boolean = false,
    val publishDate: String = "",
)
