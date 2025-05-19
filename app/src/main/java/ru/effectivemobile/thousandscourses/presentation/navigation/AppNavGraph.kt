package ru.effectivemobile.thousandscourses.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import ru.effectivemobile.account_impl.presentation.navigation.AccountNavGraph
import ru.effectivemobile.authorization_impl.presentation.navigation.AuthorizationNavGraph
import ru.effectivemobile.favorites_impl.presentation.navigation.FavoritesNavGraph
import ru.effectivemobile.feature_api.Routes
import ru.effectivemobile.feature_api.register
import ru.effectivemobile.main_impl.presentation.navigation.MainNavGraph
import ru.effectivemobile.menu_impl.presentation.navigation.MenuNavGraph
import ru.effectivemobile.onboarding_impl.presentation.navigation.OnboardingNavGraph

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Thousand Courses")

@Composable
internal fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = findStartDestination(),
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

@Composable
private fun findStartDestination(): String {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isAuthorizedKey = booleanPreferencesKey(Routes.KEY_IS_AUTHORIZED)

    val isAuthorized = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isAuthorized.value = scope.async {
            context.dataStore.data.map { preference ->
                preference[isAuthorizedKey] == true
            }.last()
        }.await()
    }

    return when(isAuthorized.value){
        true -> MenuNavGraph(
            MainNavGraph(),
            FavoritesNavGraph(),
            AccountNavGraph(),
            ).route

        false -> OnboardingNavGraph().route
    }
}