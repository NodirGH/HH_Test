package app.market.data.repositories

import app.market.data.home.AllCourseDto
import app.market.data.home.CourseDto
import app.market.data.local.AppPreferences
import app.market.data.local.DisplayableItem
import app.market.data.local.database.CoursesDao
import app.market.data.remote.mapper.toCourseDto
import app.market.data.remote.mapper.toCoursesDatabase
import app.market.data.remote.service.HomeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MainRepository {
    suspend fun getCourses(): ArrayList<DisplayableItem>
    suspend fun addAllCourse(courses: List<CourseDto>)
    suspend fun login(code: String): Boolean
    suspend fun logout()
    suspend fun getFavoriteCourses(): Flow<List<CourseDto>>
    suspend fun addFavoriteCourse(course: CourseDto)
    suspend fun removeFavoriteCourse(id: Int)
}

class MainRepositoryImpl @Inject constructor(
    private val service: HomeService,
    private val preferences: AppPreferences,
    private val coursesDao: CoursesDao
) : MainRepository {

    override suspend fun getCourses(): ArrayList<DisplayableItem> {
        if (!preferences.isCoursesAddedToDatabase) {
            val data = service.readCoursesFromAssets()
            addAllCourse(data?.courses?.map { it.toCourseDto() } ?: emptyList())
        }

        val courses = coursesDao.getCourses()
        val displayableItem = ArrayList<DisplayableItem>()
        val mappedCourses = courses.map { it.toCourseDto() }
        mappedCourses.forEach {
            if (it.hasLike) {
                addFavoriteCourse(it)
            }
        }

        displayableItem.add(AllCourseDto(mappedCourses))
        return displayableItem
    }

    override suspend fun addAllCourse(courses: List<CourseDto>) {
        coursesDao.addAllCourse(courses.map { it.toCoursesDatabase() })
        preferences.isCoursesAddedToDatabase = true
    }

    override suspend fun login(code: String): Boolean {
        preferences.isUserRegister = true
        return true
    }

    override suspend fun logout() {
        preferences.isUserRegister = false
        preferences.isCoursesAddedToDatabase = false
    }

    override suspend fun getFavoriteCourses(): Flow<List<CourseDto>> {
        return coursesDao.getFavoriteCourses().map { list -> list.map { it.toCourseDto() } }
    }

    override suspend fun addFavoriteCourse(course: CourseDto) {
        coursesDao.updateFavorite(course.id, true)
    }

    override suspend fun removeFavoriteCourse(id: Int) {
        coursesDao.updateFavorite(id = id, false)
        getFavoriteCourses()
    }
}