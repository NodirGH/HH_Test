package app.market.hh_test.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.market.data.home.CourseDto
import app.market.hh_test.R
import app.market.hh_test.databinding.ItemCourseBinding
import app.market.toolkit.string_ext.formatToRussianDate

class CoursesAdapter() : RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder>() {

    private val courses = ArrayList<CourseDto>()

    private lateinit var courseListener: CourseClickListener

    fun setOnCourseCLickListener(listener: CourseClickListener) {
        courseListener = listener
    }

    interface CourseClickListener {
        fun onCourseClick(courseDto: CourseDto)
        fun onAddCourseToFavorite(courseDto: CourseDto)
        fun onRemoveCourseFromFavorite(courseDto: CourseDto, index: Int)
        fun onSortCourses()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    inner class CoursesViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: CourseDto) {
            binding.tvCourseTitle.text = course.title
            binding.tvCourseDescription.text = course.text
            binding.tvPrice.text = itemView.context.getString(R.string.RublePrice, course.price)
            binding.tvDate.text = formatToRussianDate(course.publishDate)
            binding.tvRating.text = course.rate
            binding.ivFavorite.setImageResource(if (course.hasLike) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark)

            binding.ivFavorite.setOnClickListener {
                if (course.hasLike) {
                    binding.ivFavorite.setImageResource(R.drawable.ic_bookmark)
                    course.hasLike = false
                    courseListener.onRemoveCourseFromFavorite(course, adapterPosition)
                } else {
                    binding.ivFavorite.setImageResource(R.drawable.ic_bookmark_filled)
                    course.hasLike = true
                    courseListener.onAddCourseToFavorite(course)
                }
            }

            binding.root.setOnClickListener {
                courseListener.onCourseClick(course)
            }
        }
    }

    fun setItems(data: List<CourseDto>) {
        courses.clear()
        courses.addAll(data)
        notifyDataSetChanged()
    }
}