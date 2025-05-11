package ru.effectivemobile.authorization_api

import ru.effectivemobile.feature_api.FeatureApi

interface AuthorizationFeatureApi: FeatureApi {
    val route: String
}