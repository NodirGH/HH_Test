package app.market.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Courses::class], version = 1, exportSchema = false)
abstract class CoursesDatabase : RoomDatabase() {

    abstract fun coursesDao(): CoursesDao

    companion object {
        @Volatile
        private var INSTANCE: CoursesDatabase? = null

        fun getDatabase(context: Context): CoursesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoursesDatabase::class.java,
                    "courses_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}