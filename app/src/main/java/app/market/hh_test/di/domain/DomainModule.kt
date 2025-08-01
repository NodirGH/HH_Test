package app.market.hh_test.di.domain

import app.market.data.repositories.MainRepository
import app.market.hh_test.ui.auth.AuthUseCase
import app.market.hh_test.ui.auth.AuthUseCaseImpl
import app.market.hh_test.ui.usecases.MainUseCase
import app.market.hh_test.ui.usecases.MainUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideHomeUseCase(
        mainRepository: MainRepository
    ): MainUseCase {
        return MainUseCaseImpl(mainRepository)
    }

    @Provides
    fun provideAuthUseCase(
        mainRepository: MainRepository
    ): AuthUseCase {
        return AuthUseCaseImpl(mainRepository)
    }
}