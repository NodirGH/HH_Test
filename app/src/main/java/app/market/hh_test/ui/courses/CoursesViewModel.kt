package app.market.hh_test.ui.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.market.data.home.AllCourseDto
import app.market.data.home.CourseDto
import app.market.data.local.DisplayableItem
import app.market.hh_test.base.BaseViewModel
import app.market.hh_test.ui.usecases.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(private val mainUseCase: MainUseCase) : BaseViewModel() {

    private val _displayableItem = MutableLiveData<List<DisplayableItem>>()
    val displayableItem: LiveData<List<DisplayableItem>> = _displayableItem

    private val _favorites = MutableLiveData<List<CourseDto>>()
    val favorites: LiveData<List<CourseDto>> = _favorites

    init {
        getCourses()
    }

    fun getCourses() {
        vmScope.launch {
            val courses = mainUseCase.getCourses()
            _displayableItem.postValue(courses)

            val favorites: ArrayList<CourseDto> = ArrayList()

            courses.forEach { displayableItem ->
                if (displayableItem is AllCourseDto) {
                    displayableItem.courses.forEach { course ->
                        if (course.hasLike) {
                            favorites.add(course)
                        }
                    }
                }
            }
            _favorites.postValue(favorites)
        }
    }
}