package ru.effectivemobile.thousandscourses.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.effectivemobile.account_impl.presentation.navigation.AccountNavGraph
import ru.effectivemobile.authorization_impl.presentation.navigation.AuthorizationNavGraph
import ru.effectivemobile.favorites_impl.presentation.navigation.FavoritesNavGraph
import ru.effectivemobile.feature_api.register
import ru.effectivemobile.main_impl.presentation.navigation.MainNavGraph
import ru.effectivemobile.menu_impl.presentation.navigation.MenuNavGraph
import ru.effectivemobile.onboarding_impl.presentation.navigation.OnboardingNavGraph

@Composable
internal fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = OnboardingNavGraph().route,
        modifier = modifier
    ) {
        register(
            OnboardingNavGraph(),
            navController,
            modifier
        )
        register(
            AuthorizationNavGraph(),
            navController,
            modifier
        )
        register(
            MenuNavGraph(
                MainNavGraph(),
                FavoritesNavGraph(),
                AccountNavGraph(),
            ),
            navController
        )
    }
}