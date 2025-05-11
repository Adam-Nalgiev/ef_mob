package ru.effectivemobile.menu_api

import ru.effectivemobile.feature_api.FeatureApi

interface MenuFeatureApi: FeatureApi {
    val route: String
}