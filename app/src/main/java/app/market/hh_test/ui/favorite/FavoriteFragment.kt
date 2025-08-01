package app.market.hh_test.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.market.data.home.CourseDto
import app.market.hh_test.databinding.FragmentFavoriteBinding
import app.market.hh_test.ui.adapters.CoursesAdapter
import app.market.hh_test.ui.courses.CoursesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), CoursesAdapter.CourseClickListener {

    @Inject
    lateinit var coursesAdapter: CoursesAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val coursesViewModel: CoursesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavoriteCourses.apply {
            adapter = coursesAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

            coursesViewModel.favorites.observe(viewLifecycleOwner) { favorites ->
                coursesAdapter.setItems(favorites)
                coursesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCourseClick(courseDto: CourseDto) {
        //TODO
    }
}