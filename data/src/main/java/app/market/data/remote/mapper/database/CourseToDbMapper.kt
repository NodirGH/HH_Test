package app.market.data.remote.mapper.database

import app.market.data.local.database.Courses
import app.market.data.remote.responses.CourseDetailsResponse

fun CourseDetailsResponse.toCoursesDb(): Courses {
    return Courses(
        id = id ?: 0,
        title = title ?: "",
        text = text ?: "",
        price = price ?: "",
        rate = rate ?: "",
        startDate = startDate ?: "",
        hasLike = hasLike ?: false,
        publishDate = publishDate ?: "",
    )
}