    package app.market.hh_test.ui.adapters.adapter_delegate

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.market.hh_test.R
import app.market.hh_test.databinding.ItemSearchBinding
import app.market.hh_test.databinding.ItemVacancyWithHeaderBinding
import app.market.data.local.DisplayableItem
import app.market.data.home.AllHeadersModel
import app.market.data.home.AllVacancyModel
import app.market.hh_test.ui.adapters.HeaderAdapter
import app.market.hh_test.ui.adapters.VacanciesAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun headerAdapterDelegate() = adapterDelegateViewBinding<AllHeadersModel, DisplayableItem, ItemSearchBinding>(
    {layoutInflater, root -> ItemSearchBinding.inflate(layoutInflater, root, false) }
) {
    bind {

        val headerAdapter = HeaderAdapter()
        binding.rvHeaders.apply {
            adapter = headerAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
        headerAdapter.setItems(item.headers)
        binding.etSearch.hint = this.context.getString(R.string.Search)
        binding.ivFilter.setOnClickListener {

        }

    }
}

fun vacanciesAdapterDelegate(vacanciesAdapter: VacanciesAdapter) = adapterDelegateViewBinding<AllVacancyModel, DisplayableItem, ItemVacancyWithHeaderBinding>(
    {layoutInflater, root -> ItemVacancyWithHeaderBinding.inflate(layoutInflater, root, false) }
) {
    bind {

        binding.rvVacancies.apply {
            adapter = vacanciesAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }

        vacanciesAdapter.setItems(item.vacancies)


    }
}