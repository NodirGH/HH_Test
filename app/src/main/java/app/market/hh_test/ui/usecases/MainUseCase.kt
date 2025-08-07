package app.market.hh_test.ui.usecases

import app.market.data.home.CourseDto
import app.market.data.local.DisplayableItem
import app.market.data.repositories.MainRepository
import javax.inject.Inject

interface MainUseCase {
    suspend fun getAllData(): ArrayList<DisplayableItem>
    suspend fun getCourses(): ArrayList<DisplayableItem>
    suspend fun addFavoriteCourse(course: CourseDto)
    suspend fun removeFavoriteCourse(course: CourseDto)
    suspend fun getFavoriteCourses(): List<CourseDto>
}

class MainUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    MainUseCase {

    override suspend fun getAllData():  ArrayList<DisplayableItem>  {
        return mainRepository.getAllData()
    }

    override suspend fun getCourses(): ArrayList<DisplayableItem>
    {
        return mainRepository.getCourses()
    }

    override suspend fun addFavoriteCourse(course: CourseDto) {
        mainRepository.addFavoriteCourse(course)
    }

    override suspend fun removeFavoriteCourse(course: CourseDto) {
        mainRepository.removeFavoriteCourse(course = course)
    }

    override suspend fun getFavoriteCourses(): List<CourseDto> {
        return mainRepository.getFavoriteCourses()
    }
}