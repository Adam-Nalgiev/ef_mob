package ru.effectivemobile.thousandscourses.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ru.effectivemobile.thousandscourses.presentation.navigation.NavGraph
import ru.effectivemobile.thousandscourses.presentation.theme.ThousandsCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThousandsCoursesTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(
                        navController = rememberNavController(),
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                    )
                }
            }
        }
    }
}