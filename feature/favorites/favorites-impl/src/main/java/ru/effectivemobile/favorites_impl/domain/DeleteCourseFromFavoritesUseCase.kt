package ru.effectivemobile.favorites_impl.domain

import ru.effectivemobile.favorites_impl.data.Repository
import javax.inject.Inject

internal class DeleteCourseFromFavoritesUseCase @Inject constructor(
    private val repository: Repository
){
    suspend fun execute(id: String){
        repository.deleteFromFavorites(id)
    }
}