package ru.effectivemobile.onboarding_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.feature_api.Routes
import ru.effectivemobile.onboarding_api.OnboardingFeatureApi
import ru.effectivemobile.onboarding_impl.presentation.view.OnboardingScreen

class OnboardingNavGraph : OnboardingFeatureApi {

    override val route: String = Routes.ONBOARDING

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            OnboardingScreen {
                navController.navigate(
                    Routes.AUTHORIZATION
                )
            }
        }
    }
}