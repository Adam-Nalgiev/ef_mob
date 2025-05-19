package ru.effectivemobile.favorites_impl.entity

internal interface Course {

    val id: String
    val title: String
    val text: String
    val price: String
    val rate: Double
    val startDate: String
    val hasLike: Boolean
    val publishDate: String
}