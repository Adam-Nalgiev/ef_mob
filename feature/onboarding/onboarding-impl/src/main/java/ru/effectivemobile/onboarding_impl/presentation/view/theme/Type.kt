package ru.effectivemobile.onboarding_impl.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.effectivemobile.onboarding_impl.R

internal val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontSize = 28.sp,
        color = White,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
)

internal val ButtonType = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontSize = 14.sp,
    color = White
)