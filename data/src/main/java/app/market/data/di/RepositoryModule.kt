package app.market.data.di

import app.market.data.local.AppPreferences
import app.market.data.remote.service.HomeService
import app.market.data.repositories.HomeRepository
import app.market.data.repositories.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
     service: HomeService,
     preferences: AppPreferences
    ): HomeRepository {
        return HomeRepositoryImpl(service, preferences)
    }
 }