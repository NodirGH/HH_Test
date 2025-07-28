package app.market.data.remote.responses

import app.market.data.home.OfferResponse

data class HHMockDataResponse(
    val offers: List<OfferResponse>,
    val vacancies: List<VacancyResponse>
)
