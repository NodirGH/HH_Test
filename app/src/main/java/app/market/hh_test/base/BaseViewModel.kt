package app.market.hh_test.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.market.toolkit.utils.LiveEvent
import app.market.toolkit.utils.SingleEvent
import app.market.toolkit.utils.SingleLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    val errorOther = SingleLiveData<Throwable>()
    var loading = LiveEvent<Boolean>()
    val handler = CoroutineExceptionHandler { _, exception ->
        errorProcess(exception)
    }

    val vmScope = viewModelScope + handler + Dispatchers.IO

    fun errorProcess(throwable: Throwable, f: ((t: Throwable) -> Unit)? = null) {
        viewModelScope.launch {
            loading.postValue(false)
            throwable.printStackTrace()
            Timber.d(throwable.message)
            errorOther.postValue(SingleEvent(throwable))
        }
    }

    fun CoroutineScope.loadingLaunch(suspendCall: suspend () -> Unit): Job {
        loading.postValue(true)
        return launch {
            suspendCall.invoke()
            loading.postValue(false)
        }
    }

    fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
        try {
            collect { value -> emit(value) }
        } catch (e: Throwable) {
            errorProcess(e)
        }
    }
}