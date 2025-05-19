package ru.effectivemobile.course_description_impl.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun CourseDescriptionScreen(
    modifier: Modifier = Modifier,

    ) {
    Box(
        modifier.fillMaxSize()
    ) {
        Text(
            "В ТЗ ничего об этом экране не сказано, хоть и есть в макете. Так его нужно делать или нет?",
            Modifier.align(Alignment.Center)
        )
    }
}
