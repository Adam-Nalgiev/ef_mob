package ru.effectivemobile.main_impl.data.model.dto

import androidx.compose.runtime.Immutable
import ru.effectivemobile.main_impl.entity.Course

@Immutable
internal data class CourseDto(

    override val id: String,
    override val title: String,
    override val text: String,
    override val price: String,
    override val rate: Double,
    override val startDate: String,
    override val hasLike: Boolean,
    override val publishDate: String

) : Course