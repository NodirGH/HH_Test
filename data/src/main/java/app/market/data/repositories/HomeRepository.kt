package app.market.data.repositories

import app.market.data.home.AllCourseDto
import app.market.data.home.AllHeadersModel
import app.market.data.home.AllVacancyModel
import app.market.data.home.CourseDto
import app.market.data.home.HeadersModel
import app.market.data.home.VacancyModel
import app.market.data.local.AppPreferences
import app.market.data.local.DisplayableItem
import app.market.data.remote.service.HomeService
import javax.inject.Inject

interface HomeRepository {
    suspend fun getAllData(): ArrayList<DisplayableItem>
    suspend fun getCourses(): ArrayList<DisplayableItem>
    suspend fun login(code: String): Boolean
    suspend fun logout()
}

class HomeRepositoryImpl @Inject constructor(
    private val service: HomeService,
    private val preferences: AppPreferences
) : HomeRepository {

    override suspend fun getAllData(): ArrayList<DisplayableItem> {
        val allData = service.readMockDataFromAssets()

        val displayableItem = ArrayList<DisplayableItem>()
        val vacancies = allData?.vacancies?.map { vacancy ->
            VacancyModel(
                id = vacancy.id ?: "",
                lookingNumber = vacancy.lookingNumber ?: 0,
                title = vacancy.title ?: "",
                town = vacancy.address.town ?: "",
                street = vacancy.address.street ?: "",
                house = vacancy.address.house ?: "",
                company = vacancy.company ?: "",
                experiencePreviewText = vacancy.experience.previewText ?: "",
                experienceText = vacancy.experience.text ?: "",
                publishedDate = vacancy.publishedDate ?: "",
                isFavorite = vacancy.isFavorite ?: false,
                fullSalary = vacancy.salary.full ?: "",
                shortSalary = vacancy.salary.short ?: "",
                schedules = vacancy.schedules ?: emptyList(),
                description = vacancy.description ?: "",
                responsibilities = vacancy.responsibilities ?: "",
                questions = vacancy.questions ?: emptyList(),
                appliedNumber = vacancy.appliedNumber ?: 0
            )
        }

        val header = allData?.offers?.map { offerResponse ->
            HeadersModel(
                id = offerResponse.id ?: "",
                title = offerResponse.title ?: "",
                link = offerResponse.link ?: "",
                buttonText = offerResponse.button?.text ?: "",
            )
        }

        displayableItem.add(AllHeadersModel(header ?: emptyList()))
        displayableItem.add(AllVacancyModel(vacancies ?: emptyList()))

        return displayableItem
    }

    override suspend fun getCourses(): ArrayList<DisplayableItem> {
        val data = service.readCoursesFromAssets()

        val displayableItem = ArrayList<DisplayableItem>()
        val courses = data?.courses?.map { course ->
            CourseDto(
                id = course.id ?: 0,
                title = course.title ?: "",
                text = course.text ?: "",
                price = course.price ?: "",
                rate = course.rate ?: "",
                startDate = course.startDate ?: "",
                hasLike = course.hasLike ?: false,
                publishDate = course.publishDate ?: "",
            )
        }

        displayableItem.add(AllCourseDto(courses ?: emptyList()))
        return displayableItem
    }

    override suspend fun login(code: String): Boolean {
        preferences.isUserRegister = true
        return true
    }

    override suspend fun logout() {
        preferences.isUserRegister = false
    }
}