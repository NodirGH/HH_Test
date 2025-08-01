package app.market.hh_test.ui.usecases

import app.market.data.local.DisplayableItem
import app.market.data.repositories.MainRepository
import javax.inject.Inject

interface MainUseCase {
    suspend fun getAllData(): ArrayList<DisplayableItem>
    suspend fun getCourses(): ArrayList<DisplayableItem>
}

class MainUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    MainUseCase {

    override suspend fun getAllData():  ArrayList<DisplayableItem>  {
        return mainRepository.getAllData()
    }

    override suspend fun getCourses(): ArrayList<DisplayableItem> {
        return mainRepository.getCourses()
    }
}