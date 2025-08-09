package app.market.data.di

import app.market.data.local.AppPreferences
import app.market.data.local.database.CoursesDao
import app.market.data.remote.service.HomeService
import app.market.data.repositories.MainRepository
import app.market.data.repositories.MainRepositoryImpl
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
     preferences: AppPreferences,
     coursesDao: CoursesDao
    ): MainRepository {
        return MainRepositoryImpl(service, preferences, coursesDao
        )
    }
 }