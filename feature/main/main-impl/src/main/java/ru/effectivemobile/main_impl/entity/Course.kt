package ru.effectivemobile.main_impl.entity

interface Course {

    val id: String
    val title: String
    val text: String
    val price: String
    val rate: Double
    val startDate: String
    val hasLike: Boolean
    val publishDate: String
}