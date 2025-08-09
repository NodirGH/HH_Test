package app.market.hh_test.ui.usecases

import app.market.data.home.CourseDto
import app.market.data.local.DisplayableItem
import app.market.data.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MainUseCase {
    suspend fun getCourses(isSorting: Boolean = false): ArrayList<DisplayableItem>
    suspend fun addFavoriteCourse(course: CourseDto)
    suspend fun removeFavoriteCourse(id: Int)
    suspend fun getFavoriteCourses(): Flow<List<CourseDto>>
}

class MainUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    MainUseCase {

    override suspend fun getCourses(isSorting: Boolean): ArrayList<DisplayableItem> {
        return mainRepository.getCourses(isSorting = isSorting)
    }

    override suspend fun addFavoriteCourse(course: CourseDto) {
        mainRepository.addFavoriteCourse(course)
    }

    override suspend fun removeFavoriteCourse(id: Int) {
        mainRepository.removeFavoriteCourse(id = id)
    }

    override suspend fun getFavoriteCourses(): Flow<List<CourseDto>> {
        return mainRepository.getFavoriteCourses()
    }
}