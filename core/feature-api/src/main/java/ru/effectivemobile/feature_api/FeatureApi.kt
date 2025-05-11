package ru.effectivemobile.feature_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier = Modifier
    )
}

fun NavGraphBuilder.register(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureApi.registerGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}