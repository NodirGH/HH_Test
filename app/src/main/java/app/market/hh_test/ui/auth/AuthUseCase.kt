package app.market.hh_test.ui.auth

import app.market.data.repositories.HomeRepository
import javax.inject.Inject

interface AuthUseCase {
    suspend fun login(code: String): Boolean
    suspend fun logout()
}

class AuthUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository): AuthUseCase {
    override suspend fun login(code: String): Boolean {
        return homeRepository.login(code)
    }

    override suspend fun logout() {
        homeRepository.logout()
    }
}