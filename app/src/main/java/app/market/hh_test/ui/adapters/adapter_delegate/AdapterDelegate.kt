package app.market.hh_test.ui.adapters.adapter_delegate

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.market.data.home.AllCourseDto
import app.market.data.local.DisplayableItem
import app.market.hh_test.databinding.ItemCourseWithTitleBinding
import app.market.hh_test.ui.adapters.CoursesAdapter
import app.market.hh_test.ui.adapters.CoursesAdapter.CourseClickListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun coursesAdapterDelegate(coursesAdapter: CoursesAdapter, listener: CourseClickListener) =
    adapterDelegateViewBinding<AllCourseDto, DisplayableItem, ItemCourseWithTitleBinding>(
        { layoutInflater, root ->
            ItemCourseWithTitleBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            val courseListener: CourseClickListener = listener
            binding.rvCourses.apply {
                adapter = coursesAdapter
                layoutManager = LinearLayoutManager(itemView.context, RecyclerView.VERTICAL, false)
                itemAnimator = DefaultItemAnimator()
            }
            coursesAdapter.setItems(item.courses)
            binding.tvSortByPublishDate.setOnClickListener {
                courseListener.onSortCourses()
            }
        }
    }