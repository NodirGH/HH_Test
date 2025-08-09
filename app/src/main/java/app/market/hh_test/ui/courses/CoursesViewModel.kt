package app.market.hh_test.ui.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.market.data.local.DisplayableItem
import app.market.hh_test.base.BaseViewModel
import app.market.hh_test.ui.usecases.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val mainUseCase: MainUseCase

) : BaseViewModel() {

    private val _displayableItem = MutableLiveData<List<DisplayableItem>>()
    val displayableItem: LiveData<List<DisplayableItem>> = _displayableItem
    var isCoursesSorted = false

    init {
        getCourses()
    }

    fun getCourses() {
        vmScope.launch {
            val courses = mainUseCase.getCourses(isSorting = isCoursesSorted)
            _displayableItem.postValue(courses)
        }
    }
}