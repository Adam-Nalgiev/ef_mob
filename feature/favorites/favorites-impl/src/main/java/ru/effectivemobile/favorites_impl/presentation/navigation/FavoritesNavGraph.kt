package ru.effectivemobile.favorites_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.favorites_api.FavoritesFeatureApi
import ru.effectivemobile.favorites_impl.presentation.FavoritesScreen
import ru.effectivemobile.feature_api.Routes

class FavoritesNavGraph: FavoritesFeatureApi{
    override val route: String = Routes.FAVORITES

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(Routes.FAVORITES) {
            FavoritesScreen()
        }
    }
}