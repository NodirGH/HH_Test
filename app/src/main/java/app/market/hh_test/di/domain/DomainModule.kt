package app.market.hh_test.di.domain

import app.market.data.repositories.HomeRepository
import app.market.hh_test.ui.auth.AuthUseCase
import app.market.hh_test.ui.auth.AuthUseCaseImpl
import app.market.hh_test.ui.usecases.HomeUseCase
import app.market.hh_test.ui.usecases.HomeUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCase {
        return HomeUseCaseImpl(homeRepository)
    }

    @Provides
    fun provideAuthUseCase(
        homeRepository: HomeRepository
    ): AuthUseCase {
        return AuthUseCaseImpl(homeRepository)
    }
}