package app.market.hh_test.ui.usecases

import app.market.data.local.DisplayableItem
import app.market.data.repositories.HomeRepository
import javax.inject.Inject

interface HomeUseCase {
    suspend fun getAllData(): ArrayList<DisplayableItem>
}

class HomeUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) :
    HomeUseCase {

    override suspend fun getAllData():  ArrayList<DisplayableItem>  {
        return homeRepository.getAllData()
    }
}