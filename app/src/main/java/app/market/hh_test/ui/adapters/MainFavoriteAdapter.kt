package app.market.hh_test.ui.adapters

import app.market.data.local.DisplayableItem
import app.market.hh_test.ui.adapters.adapter_delegate.favoriteCoursesAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class MainFavoriteAdapter(coursesAdapter: CoursesAdapter) :
    ListDelegationAdapter<List<DisplayableItem>>(
        favoriteCoursesAdapterDelegate(coursesAdapter)
    )