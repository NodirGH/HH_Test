package app.market.hh_test.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin
    private val _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean> = _isLoginButtonEnabled
    var loginText = ""
    var passwordText = ""

    init {
        checkLoginButton()
    }

    fun login(code: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            val result = authUseCase.login(code)
            _isLogin.postValue(result)
        }
    }

    fun checkLoginButton() {
        _isLoginButtonEnabled.value = loginText.isNotEmpty() && passwordText.isNotEmpty()
    }
}