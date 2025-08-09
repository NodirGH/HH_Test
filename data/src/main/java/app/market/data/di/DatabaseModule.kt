package app.market.data.di

import android.content.Context
import androidx.room.Room
import app.market.data.local.database.CoursesDao
import app.market.data.local.database.CoursesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CoursesDatabase {
        return Room.databaseBuilder(
            context,
            CoursesDatabase::class.java,
            "courses_database"
        ).build()
    }

    @Provides
    fun provideCourseDao(courseDatabase: CoursesDatabase): CoursesDao {
        return courseDatabase.coursesDao()
    }
}