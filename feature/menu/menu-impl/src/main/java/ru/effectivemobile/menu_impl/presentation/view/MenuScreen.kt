package ru.effectivemobile.menu_impl.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import ru.effectivemobile.menu_impl.presentation.view.theme.Gray
import ru.effectivemobile.menu_impl.presentation.view.theme.Green
import ru.effectivemobile.menu_impl.presentation.view.theme.LightGray

@Composable
fun MenuScreen(
    mainScreen: FeatureApi,
    favoritesScreen: FeatureApi,
    accountScreen: FeatureApi,
    courseDescription: FeatureApi,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(
                containerColor = Gray
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                /** Есть более лакончиный метод, путем создания sealed класса, он выглядит приличнее, без множества циклов when, возможно сделаю если время будет*/
                repeat(3) { index ->
                    val route = when(index) {
                        0 -> Routes.MAIN
                        1 -> Routes.FAVORITES
                        2 -> Routes.ACCOUNT
                        else -> Routes.MAIN
                    }
                    NavigationBarItem(
                        icon = {
                            when (index) {
                                0 -> Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_main),
                                    stringResource(R.string.descr_main)
                                )

                                1 -> Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_favorites),
                                    stringResource(R.string.descr_favorites)
                                )

                                2 -> Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_account),
                                    stringResource(R.string.descr_account)
                                )
                            }

                        },
                        label = {
                            when (index) {
                                0 -> Text(stringResource(R.string.main))

                                1 -> Text(stringResource(R.string.favorite))

                                2 -> Text(stringResource(R.string.account))
                            }
                        },
                        selected = currentRoute == route,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Green,
                            selectedTextColor = Green,
                            indicatorColor = LightGray
                        ),
                        onClick = {
                            navHostController.navigate(route) {
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
            register(
                navController = navHostController,
                featureApi = courseDescription
            )
        }
    }
}