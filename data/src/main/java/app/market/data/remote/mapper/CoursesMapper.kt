package app.market.data.remote.mapper

import app.market.data.home.CourseDto
import app.market.data.local.database.Courses
import app.market.data.remote.responses.CourseDetailsResponse

fun CourseDetailsResponse.toCourseDto(): CourseDto {
    return CourseDto(
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

fun Courses.toCourseDto(): CourseDto {
    return CourseDto(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate,
    )
}