package ru.effectivemobile.account_api

import ru.effectivemobile.feature_api.FeatureApi

interface AccountFeatureApi: FeatureApi {
    val route: String
}