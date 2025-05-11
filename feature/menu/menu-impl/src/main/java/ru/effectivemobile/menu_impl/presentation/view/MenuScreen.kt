package ru.effectivemobile.menu_impl.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.effectivemobile.feature_api.FeatureApi
import ru.effectivemobile.feature_api.Routes
import ru.effectivemobile.feature_api.register
import ru.effectivemobile.menu_impl.R

@Composable
fun MenuScreen(
    mainScreen: FeatureApi,
    favoritesScreen: FeatureApi,
    accountScreen: FeatureApi,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_main),
                            stringResource(R.string.descr_main)
                        )
                    },
                    label = { Text(Routes.MAIN) },
                    selected = currentRoute == Routes.MAIN,
                    onClick = {
                        navHostController.navigate(Routes.MAIN) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_favorites),
                            stringResource(R.string.descr_favorites)
                        )
                    },
                    label = { Text(Routes.FAVORITES) },
                    selected = currentRoute == Routes.FAVORITES,
                    onClick = {
                        navHostController.navigate(Routes.FAVORITES) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_account),
                            stringResource(R.string.descr_account)
                        )
                    },
                    label = { Text(Routes.ACCOUNT) },
                    selected = currentRoute == Routes.ACCOUNT,
                    onClick = {
                        navHostController.navigate(Routes.ACCOUNT) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Routes.MAIN,
            modifier = Modifier.padding(innerPadding)
        ) {
            register(
                navController = navHostController,
                featureApi = mainScreen
            )
            register(
                navController = navHostController,
                featureApi = favoritesScreen
            )
            register(
                navController = navHostController,
                featureApi = accountScreen
            )
        }
    }

}