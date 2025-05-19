package ru.effectivemobile.main_impl.data

import android.util.Log
import ru.effectivemobile.main_impl.data.database.Dao
import ru.effectivemobile.main_impl.data.model.dbo.CourseDbo
import ru.effectivemobile.main_impl.data.model.dto.CourseDto
import ru.effectivemobile.main_impl.data.network.Client
import ru.effectivemobile.main_impl.entity.Course
import javax.inject.Inject

internal class Repository @Inject constructor(
    private val client: Client,
    private val database: Dao
) {
    suspend fun getCourseList(): List<CourseDto>? {
        return try {
            val result = client.retrofit.getCoursesList().courses
            saveCourses(courseDtoToDboMapper(result))
            Log.d("REQUEST", "SUCCESS $result")
            result
        } catch (e: Exception) {
            Log.d("REQUEST", "FAILURE $e")
            null
        }
    }

    suspend fun saveToFavorites(course: Course) {
        //какой-то запрос на сервер

        val courseDto = course as? CourseDto
        if (courseDto != null)
            database.insert(courseDtoToDboMapper(listOf(courseDto)).first())
    }

    suspend fun deleteFromFavorites(id: String) {
        database.delete(id)
    }

    private suspend fun saveCourses(courses: List<CourseDbo>) {
        courses.forEach { course ->
            database.insert(course)
        }
    }

    private fun courseDtoToDboMapper(coursesDto: List<CourseDto>): List<CourseDbo> {
        return coursesDto.map { course ->
            CourseDbo(
                id = course.id,
                title = course.title,
                text = course.text,
                price = course.price,
                rate = course.rate,
                startDate = course.startDate,
                hasLike = course.hasLike,
                publishDate = course.publishDate
            )
        }
    }
}