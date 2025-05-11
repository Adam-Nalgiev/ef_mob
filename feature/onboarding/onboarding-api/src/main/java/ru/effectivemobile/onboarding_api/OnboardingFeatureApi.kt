package ru.effectivemobile.onboarding_api

import ru.effectivemobile.feature_api.FeatureApi

interface OnboardingFeatureApi: FeatureApi {
    val route: String
}