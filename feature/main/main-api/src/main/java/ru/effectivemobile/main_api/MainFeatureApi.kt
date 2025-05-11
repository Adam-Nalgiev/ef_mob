package ru.effectivemobile.main_api

import ru.effectivemobile.feature_api.FeatureApi

interface MainFeatureApi: FeatureApi {
    val route: String
}