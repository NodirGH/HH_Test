package app.market.hh_test.di

import app.market.hh_test.ui.adapters.CoursesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideCoursesAdapter() = CoursesAdapter()

}