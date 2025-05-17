package ru.effectivemobile.main_impl.domain

import ru.effectivemobile.main_impl.data.Repository
import ru.effectivemobile.main_impl.entity.Course
import javax.inject.Inject

class GetCoursesListUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): List<Course>? {
        return repository.getCourseList()
    }
}