package app.market.hh_test.ui.adapters

import app.market.data.local.DisplayableItem
import app.market.hh_test.ui.adapters.adapter_delegate.coursesAdapterDelegate
import app.market.hh_test.ui.adapters.adapter_delegate.headerAdapterDelegate
import app.market.hh_test.ui.adapters.adapter_delegate.vacanciesAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class MainAdapter(vacanciesAdapter: VacanciesAdapter, coursesAdapter: CoursesAdapter) :
    ListDelegationAdapter<List<DisplayableItem>>(
        headerAdapterDelegate(),
        vacanciesAdapterDelegate(vacanciesAdapter),
        coursesAdapterDelegate(coursesAdapter)
    )