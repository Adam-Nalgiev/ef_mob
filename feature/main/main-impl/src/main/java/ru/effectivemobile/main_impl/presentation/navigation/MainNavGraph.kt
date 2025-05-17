package ru.effectivemobile.main_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.feature_api.Routes
import ru.effectivemobile.main_api.MainFeatureApi
import ru.effectivemobile.main_impl.presentation.view.MainScreen

class MainNavGraph: MainFeatureApi {
    override val route: String = Routes.MAIN

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(Routes.MAIN) {
            MainScreen {
                navController.navigate(
                    Routes.COURSE_DESCRIPTION
                )
            }
        }
    }
}