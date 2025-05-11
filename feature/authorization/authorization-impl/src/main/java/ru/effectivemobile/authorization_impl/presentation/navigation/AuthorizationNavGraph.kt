package ru.effectivemobile.authorization_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.authorization_api.AuthorizationFeatureApi
import ru.effectivemobile.authorization_impl.presentation.view.AuthorizationScreen
import ru.effectivemobile.feature_api.Routes

class AuthorizationNavGraph : AuthorizationFeatureApi {

    override val route: String = Routes.AUTHORIZATION

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            AuthorizationScreen {
                navController.navigate(
                    Routes.MENU
                )
            }
        }
    }
}