package ru.effectivemobile.favorites_impl.data

import android.util.Log
import ru.effectivemobile.favorites_impl.data.database.Dao
import ru.effectivemobile.favorites_impl.data.model.dbo.CourseDbo
import ru.effectivemobile.favorites_impl.entity.Course
import javax.inject.Inject

internal class Repository @Inject constructor(
    private val database: Dao
) {
    suspend fun getFavoritesList(): List<Course> {
        return database.getFavorites()
    }

    suspend fun saveToFavorites(course: Course) {
        //какой-то запрос на сервер

        val courseDto = course as? CourseDbo
        Log.d("SAVE", course.toString())
        if (courseDto != null)
            database.insert(courseDto)
    }

    suspend fun deleteFromFavorites(id: String) {
        database.delete(id)
    }
}