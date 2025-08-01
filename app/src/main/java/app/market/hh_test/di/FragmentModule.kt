package app.market.hh_test.di

import app.market.hh_test.ui.adapters.CoursesAdapter
import app.market.hh_test.ui.adapters.HeaderAdapter
import app.market.hh_test.ui.adapters.VacanciesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideVacanciesAdapter() = VacanciesAdapter()

    @Provides
    fun provideCoursesAdapter() = CoursesAdapter()

//    @Provides
//    fun provideQuestionsAdapter() = QuestionsAdapter()

    @Provides
    fun provideHeaderAdapter() = HeaderAdapter()
}