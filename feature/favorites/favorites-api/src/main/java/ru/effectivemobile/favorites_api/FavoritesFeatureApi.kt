package ru.effectivemobile.favorites_api

import ru.effectivemobile.feature_api.FeatureApi

interface FavoritesFeatureApi: FeatureApi {
    val route: String
}