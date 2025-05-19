package ru.effectivemobile.favorites_impl.domain

import ru.effectivemobile.favorites_impl.data.Repository
import ru.effectivemobile.favorites_impl.entity.Course
import javax.inject.Inject

internal class SaveCourseToFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(course: Course) {
        repository.saveToFavorites(course)
    }
}