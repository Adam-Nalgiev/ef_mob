package ru.effectivemobile.main_impl.domain

import ru.effectivemobile.main_impl.data.Repository
import ru.effectivemobile.main_impl.entity.Course
import javax.inject.Inject

class SaveCourseToFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(course: Course) {
        repository.saveToFavorites(course)
    }
}