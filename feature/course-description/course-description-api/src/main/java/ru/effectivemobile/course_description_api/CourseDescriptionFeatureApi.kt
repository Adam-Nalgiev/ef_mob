package ru.effectivemobile.course_description_api

import ru.effectivemobile.feature_api.FeatureApi

interface CourseDescriptionFeatureApi: FeatureApi {
    val route: String
}