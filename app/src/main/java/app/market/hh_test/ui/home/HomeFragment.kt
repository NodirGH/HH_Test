package app.market.hh_test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.market.data.home.CourseDto
import app.market.data.home.VacancyModel
import app.market.hh_test.databinding.FragmentHomeBinding
import app.market.hh_test.ui.adapters.CoursesAdapter
import app.market.hh_test.ui.adapters.MainAdapter
import app.market.hh_test.ui.adapters.VacanciesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), VacanciesAdapter.VacancyClickListener, CoursesAdapter.CourseClickListener {

    @Inject
    lateinit var vacanciesAdapter: VacanciesAdapter

    @Inject
    lateinit var coursesAdapter: CoursesAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainAdapter = MainAdapter(vacanciesAdapter, coursesAdapter)
        homeViewModel.getCourses()
        vacanciesAdapter.setOnVacancyCLickListener(this)
        coursesAdapter.setOnCourseCLickListener(this)

        binding.rvHome.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

            homeViewModel.displayableItem.observe(viewLifecycleOwner) {
                mainAdapter.items = it
                if (it.isNotEmpty()) {
                    mainAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onVacancyClick(vacancyModel: VacancyModel) {
        //TODO: Navigate to DetailedVacancyFragment
//        val action =
//            HomeFragmentDirections.actionHomeFragmentToDetailedVacancyFragment(vacancyModel)
//        findNavController().navigate(action)
    }

    override fun onCourseClick(courseDto: CourseDto) {
        Toast.makeText(requireContext(), "Course clicked", Toast.LENGTH_SHORT).show()
    }
}