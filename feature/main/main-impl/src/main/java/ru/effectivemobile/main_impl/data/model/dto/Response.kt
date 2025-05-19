package ru.effectivemobile.main_impl.data.model.dto

import androidx.compose.runtime.Immutable

@Immutable
internal data class Response(
    val courses: List<CourseDto>
)
