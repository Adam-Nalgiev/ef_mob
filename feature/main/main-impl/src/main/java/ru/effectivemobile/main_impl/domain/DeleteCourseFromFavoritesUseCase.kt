package ru.effectivemobile.main_impl.domain

import ru.effectivemobile.main_impl.data.Repository
import javax.inject.Inject

internal class DeleteCourseFromFavoritesUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun execute(id: String){
        repository.deleteFromFavorites(id)
    }
}