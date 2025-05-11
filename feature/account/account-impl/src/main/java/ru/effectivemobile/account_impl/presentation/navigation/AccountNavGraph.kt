package ru.effectivemobile.account_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.account_api.AccountFeatureApi
import ru.effectivemobile.account_impl.presentation.AccountScreen
import ru.effectivemobile.feature_api.Routes

class AccountNavGraph: AccountFeatureApi {
    override val route: String = Routes.ACCOUNT

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(Routes.ACCOUNT) {
            AccountScreen()
        }
    }

}