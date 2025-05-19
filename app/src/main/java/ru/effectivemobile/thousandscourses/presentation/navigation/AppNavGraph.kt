package ru.effectivemobile.thousandscourses.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.effectivemobile.account_impl.presentation.navigation.AccountNavGraph
import ru.effectivemobile.authorization_impl.presentation.navigation.AuthorizationNavGraph
import ru.effectivemobile.course_description_impl.presentation.navigation.CourseDescriptionNavGraph
import ru.effectivemobile.datastore.AppDatastore
import ru.effectivemobile.datastore.AppDatastore.dataStore
import ru.effectivemobile.favorites_impl.presentation.navigation.FavoritesNavGraph
import ru.effectivemobile.feature_api.register
import ru.effectivemobile.main_impl.presentation.navigation.MainNavGraph
import ru.effectivemobile.menu_impl.presentation.navigation.MenuNavGraph
import ru.effectivemobile.onboarding_impl.presentation.navigation.OnboardingNavGraph

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = findStartDestination(context).collectAsState(OnboardingNavGraph().route).value,
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
                CourseDescriptionNavGraph()
            ),
            navController
        )
    }
}

private fun findStartDestination(context: Context): Flow<String> {
    val isAuthorizedKey = booleanPreferencesKey(AppDatastore.KEY_IS_AUTHORIZED)

    var isAuth = false

    return context.dataStore.data.map {
        isAuth = it[isAuthorizedKey] == true
        when (isAuth) {
            true -> MenuNavGraph(
                MainNavGraph(),
                FavoritesNavGraph(),
                AccountNavGraph(),
                CourseDescriptionNavGraph()
            ).route

            false -> OnboardingNavGraph().route
        }
    }
}