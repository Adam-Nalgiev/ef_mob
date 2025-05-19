package ru.effectivemobile.course_description_impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.effectivemobile.course_description_api.CourseDescriptionFeatureApi
import ru.effectivemobile.course_description_impl.presentation.view.CourseDescriptionScreen
import ru.effectivemobile.feature_api.Routes

class CourseDescriptionNavGraph : CourseDescriptionFeatureApi {
    override val route: String = Routes.COURSE_DESCRIPTION

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route) {
            CourseDescriptionScreen(
                modifier = modifier
            )
        }
    }
}