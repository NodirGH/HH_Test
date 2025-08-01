package app.market.hh_test.ui.auth

import app.market.data.repositories.MainRepository
import javax.inject.Inject

interface AuthUseCase {
    suspend fun login(code: String): Boolean
    suspend fun logout()
}

class AuthUseCaseImpl @Inject constructor(private val mainRepository: MainRepository): AuthUseCase {
    override suspend fun login(code: String): Boolean {
        return mainRepository.login(code)
    }

    override suspend fun logout() {
        mainRepository.logout()
    }
}