package app.market.hh_test.ui.adapters

import app.market.data.local.DisplayableItem
import app.market.hh_test.ui.adapters.adapter_delegate.coursesAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class MainAdapter(coursesAdapter: CoursesAdapter, listener: CoursesAdapter.CourseClickListener) :
    ListDelegationAdapter<List<DisplayableItem>>(
        coursesAdapterDelegate(coursesAdapter, listener = listener)
    )