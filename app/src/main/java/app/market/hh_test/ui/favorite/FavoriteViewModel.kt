package app.market.hh_test.ui.favorite

import androidx.lifecycle.viewModelScope
import app.market.data.home.CourseDto
import app.market.hh_test.base.BaseViewModel
import app.market.hh_test.ui.usecases.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : BaseViewModel() {

    private val _favorites = MutableStateFlow<List<CourseDto>>(emptyList())
    val favorites: StateFlow<List<CourseDto>> = _favorites

    init {
        viewModelScope.launch {
            mainUseCase.getFavoriteCourses().collect {
                _favorites.value = it
            }
        }
    }

    fun addFavoriteCourse(course: CourseDto) {
        vmScope.launch {
            mainUseCase.addFavoriteCourse(course = course)
        }
    }

    fun removeFavoriteCourse(course: CourseDto, index: Int) {
        vmScope.launch {
            mainUseCase.removeFavoriteCourse(course = course)
        }
    }
}