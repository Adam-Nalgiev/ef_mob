package ru.effectivemobile.onboarding_impl.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.effectivemobile.onboarding_impl.R
import ru.effectivemobile.onboarding_impl.presentation.view.theme.ButtonType
import ru.effectivemobile.onboarding_impl.presentation.view.theme.Green
import ru.effectivemobile.onboarding_impl.presentation.view.theme.Typography

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onNavigateTo: () -> Unit) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            TopText()

            BackgroundImage()
        }

        NextButton(
            onNavigateTo = onNavigateTo
        )
    }
}

@Composable
private fun TopText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.thousand_courses),
        style = Typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = modifier
            .widthIn(max = 200.dp)
            .padding(bottom = 32.dp)
    )
}

/** Он прокручивается, но размер изображения не оптимальный */
@Composable
private fun BackgroundImage(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Image(
        painter = painterResource(R.drawable.img_courses),
        contentScale = ContentScale.FillHeight,
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 400.dp)
            .horizontalScroll(scrollState)
    )
}

@Composable
private fun NextButton(
    onNavigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onNavigateTo,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Text(text = stringResource(R.string.next), style = ButtonType)
    }
}