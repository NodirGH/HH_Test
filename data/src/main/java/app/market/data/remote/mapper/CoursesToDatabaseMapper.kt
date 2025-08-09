package app.market.data.remote.mapper

import app.market.data.home.CourseDto
import app.market.data.local.database.Courses

fun CourseDto.toCoursesDatabase(): Courses {
    return Courses(
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