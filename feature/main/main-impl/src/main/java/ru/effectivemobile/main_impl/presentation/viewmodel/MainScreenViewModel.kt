package ru.effectivemobile.main_impl.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.main_impl.domain.GetCoursesListUseCase
import ru.effectivemobile.main_impl.domain.SaveCourseToFavoritesUseCase
import ru.effectivemobile.main_impl.entity.Course
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCoursesListUseCase: GetCoursesListUseCase,
    private val saveCourseToFavoritesUseCase: SaveCourseToFavoritesUseCase
) : ViewModel() {
    private val _coursesFlow = MutableStateFlow<List<Course>>(emptyList())
    val coursesFlow = _coursesFlow.asStateFlow()

    init {
        getCourses()
    }

    fun saveToFavorites(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            saveCourseToFavoritesUseCase.execute(course)
        }
    }

    fun sortList() {
        _coursesFlow.value = _coursesFlow.value.sortedBy { it.publishDate }
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
            val courses = getCoursesListUseCase.execute()
            _coursesFlow.value = courses ?: emptyList()
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