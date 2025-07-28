package app.market.data.home

import app.market.data.local.DisplayableItem

data class AllVacancyModel(
    val vacancies: List<VacancyModel>
): DisplayableItem
