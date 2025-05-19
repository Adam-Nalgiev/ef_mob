package ru.effectivemobile.favorites_impl.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.favorites_impl.domain.DeleteCourseFromFavoritesUseCase
import ru.effectivemobile.favorites_impl.domain.GetFavoritesCoursesListUseCase
import ru.effectivemobile.favorites_impl.domain.SaveCourseToFavoritesUseCase
import ru.effectivemobile.favorites_impl.entity.Course
import javax.inject.Inject

@HiltViewModel
internal class FavoritesViewModel @Inject constructor(
    private val getFavoritesCoursesListUseCase: GetFavoritesCoursesListUseCase,
    private val saveCourseToFavoritesUseCase: SaveCourseToFavoritesUseCase,
    private val deleteCourseFromFavoritesUseCase: DeleteCourseFromFavoritesUseCase,
): ViewModel() {

    private val _coursesFlow = MutableStateFlow<List<Course>>(emptyList())
    internal val coursesFlow = _coursesFlow.asStateFlow()

    init {
        getCourses()
    }

    fun saveToFavorites(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCourseToFavoritesUseCase.execute(course)
        }
    }

    fun deleteFromFavorites(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteCourseFromFavoritesUseCase.execute(id)
        }
    }

    fun convertDate(date: String): String {
        val dateParts = date.split("-")
        val day = dateParts[2]
        val month = convertMonth(dateParts[1])
        val year = dateParts[0]

        return "$day $month $year"
    }

    private fun getCourses() {
        viewModelScope.launch {
            val courses = getFavoritesCoursesListUseCase.execute()
            _coursesFlow.value = courses
        }
    }

    private fun convertMonth(monthNum: String): String {
        val map = mapOf(
            "01" to "Января",
            "02" to "Февраля",
            "03" to "Марта",
            "04" to "Апреля",
            "05" to "Мая",
            "06" to "Июня",
            "07" to "Июля",
            "08" to "Августа",
            "09" to "Сентября",
            "10" to "Октября",
            "11" to "Ноября",
            "12" to "Декабря",
        )
        return map[monthNum] ?: ""
    }
}