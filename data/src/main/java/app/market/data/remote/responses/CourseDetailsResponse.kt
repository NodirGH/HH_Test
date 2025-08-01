package app.market.data.remote.responses

data class CourseDetailsResponse(
    val id: Int?,
    val title: String?,
    val text: String?,
    val price: String?,
    val rate: String?,
    val startDate: String?,
    val hasLike: Boolean?,
    val publishDate: String?,
)