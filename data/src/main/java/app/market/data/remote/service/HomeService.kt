package app.market.data.remote.service

import android.content.Context
import app.market.data.remote.responses.CoursesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class HomeService @Inject constructor(val context: Context){

    fun readCoursesFromAssets(): CoursesResponse? {
        return try {
            val inputStream = context.assets.open("courses.json")
            val reader = BufferedReader(InputStreamReader(inputStream))

            val gson = Gson()
            val type = object : TypeToken<CoursesResponse>() {}.type
            val coursesData: CoursesResponse = gson.fromJson(reader, type)
            reader.close()
            coursesData
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}