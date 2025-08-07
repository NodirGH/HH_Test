package app.market.data.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferences @Inject constructor(val context: Context) {

    private var preferences: SharedPreferences = context.getSharedPreferences("HHTestPreference", Context.MODE_PRIVATE)

    var isUserRegister: Boolean
    get() = preferences.getBoolean(::isUserRegister.name, false)
    set(value) {
        preferences.edit().putBoolean(::isUserRegister.name, value).apply()
    }

    var isCoursesAddedToDatabase: Boolean
        get() = preferences.getBoolean(::isCoursesAddedToDatabase.name, false)
        set(value) {
            preferences.edit().putBoolean(::isCoursesAddedToDatabase.name, value).apply()
        }
}