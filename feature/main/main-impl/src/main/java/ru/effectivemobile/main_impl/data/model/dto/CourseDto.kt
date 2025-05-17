package ru.effectivemobile.main_impl.data.model.dto

import ru.effectivemobile.main_impl.entity.Course

data class CourseDto(

    override val id: String,
    override val title: String,
    override val text: String,
    override val price: String,
    override val rate: Double,
    override val startDate: String,
    override val hasLike: Boolean,
    override val publishDate: String

) : Course