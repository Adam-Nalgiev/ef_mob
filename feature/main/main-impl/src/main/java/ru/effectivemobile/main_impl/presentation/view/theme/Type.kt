package ru.effectivemobile.main_impl.presentation.view.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.effectivemobile.main_impl.R

internal val Typography = Typography(
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.15.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto)),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
)

internal val ButtonSmallType = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontSize = 12.sp,
    lineHeight = 15.sp,
    letterSpacing = 0.4.sp
)
internal val CaptionType = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto)),
    fontSize = 12.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.4.sp
)