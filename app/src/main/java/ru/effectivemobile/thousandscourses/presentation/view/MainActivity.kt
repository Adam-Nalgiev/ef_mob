package ru.effectivemobile.thousandscourses.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.effectivemobile.thousandscourses.presentation.navigation.AppNavGraph
import ru.effectivemobile.thousandscourses.presentation.view.theme.Dark
import ru.effectivemobile.thousandscourses.presentation.view.theme.ThousandsCoursesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThousandsCoursesTheme {
                AppNavGraph(
                    navController = rememberNavController(),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Dark)
                )
            }
        }
    }
}