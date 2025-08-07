package app.market.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoursesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAllCourse(courses: List<Courses>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCourse(course: Courses)

    @Query("SELECT * FROM courses")
    fun getCourses(): List<Courses>

    @Delete
    fun removeCourse(courses: Courses)

    @Query("SELECT * FROM courses WHERE hasLike = 1")
    fun getFavoriteCourses(): List<Courses>

    @Query("UPDATE courses SET hasLike = :isLiked WHERE id = :id")
    fun updateFavorite(id: Int, isLiked: Boolean): Unit

}