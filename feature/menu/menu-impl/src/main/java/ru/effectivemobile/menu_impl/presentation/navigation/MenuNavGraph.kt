package ru.effectivemobile.menu_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.effectivemobile.feature_api.FeatureApi
import ru.effectivemobile.feature_api.Routes
import ru.effectivemobile.menu_api.MenuFeatureApi
import ru.effectivemobile.menu_impl.presentation.view.MenuScreen

class MenuNavGraph(
    private val mainScreen: FeatureApi,
    private val favoritesScreen: FeatureApi,
    private val accountScreen: FeatureApi,
) : MenuFeatureApi {

    override val route: String = Routes.MENU

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            MenuScreen(
                mainScreen = mainScreen,
                favoritesScreen = favoritesScreen,
                accountScreen = accountScreen,
                navHostController = rememberNavController()
            )
        }
    }
}