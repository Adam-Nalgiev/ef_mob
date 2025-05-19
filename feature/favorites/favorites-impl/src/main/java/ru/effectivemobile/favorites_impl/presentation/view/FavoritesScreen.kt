package ru.effectivemobile.favorites_impl.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import ru.effectivemobile.favorites_impl.R
import ru.effectivemobile.favorites_impl.data.model.dbo.CourseDbo
import ru.effectivemobile.favorites_impl.entity.Course
import ru.effectivemobile.favorites_impl.presentation.view.theme.ButtonSmallType
import ru.effectivemobile.favorites_impl.presentation.view.theme.CaptionType
import ru.effectivemobile.favorites_impl.presentation.view.theme.Gray
import ru.effectivemobile.favorites_impl.presentation.view.theme.Green
import ru.effectivemobile.favorites_impl.presentation.view.theme.Transparent
import ru.effectivemobile.favorites_impl.presentation.view.theme.Typography
import ru.effectivemobile.favorites_impl.presentation.view.theme.White
import ru.effectivemobile.favorites_impl.presentation.viewModel.FavoritesViewModel

@Composable
internal fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigateTo: () -> Unit,
) {
    val courses = viewModel.coursesFlow.collectAsStateWithLifecycle()
    val isLoadingVisible = remember { mutableStateOf(courses.value.isEmpty()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.favorites),
            style = Typography.titleLarge,
            color = White,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )

        LoadingBar(
            visibility = isLoadingVisible,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        when {
            courses.value.isEmpty() -> isLoadingVisible.value = true
            else -> {
                CoursesList(
                    viewModel = viewModel,
                    courses = courses.value,
                    onNavigateTo = onNavigateTo
                )
                isLoadingVisible.value = false
            }
        }
    }
}

@Composable
private fun CoursesList(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel,
    onNavigateTo: () -> Unit,
    courses: List<Course>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(courses) { item ->
            CourseItem(
                courseInfo = item,
                modifier = Modifier.padding(bottom = 16.dp),
                viewModel = viewModel,
                onNavigateTo = onNavigateTo
            )
        }
    }
}

@Composable
private fun CourseItem(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel,
    courseInfo: Course,
    onNavigateTo: () -> Unit
) {
    val hazeState = rememberHazeState(true)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray, RoundedCornerShape(16.dp))
            .hazeSource(hazeState)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(R.drawable.img_course),// А где ссылка на фотографию курса??
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .heightIn(max = 160.dp)
            )
            Text(
                text = courseInfo.title,
                style = Typography.titleMedium,
                color = White,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = courseInfo.text,
                style = Typography.bodySmall,
                color = White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .alpha(0.6f)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = courseInfo.price + " " + '\u20bd',
                    style = Typography.titleMedium,
                    color = White,
                    maxLines = 1
                )
                ToDescriptionButton(onNavigateTo = onNavigateTo)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .hazeSource(hazeState)
                .padding(top = 120.dp)
        ) {
            Rate(rate = courseInfo.rate.toString())
            Date(date = viewModel.convertDate(courseInfo.publishDate), hazeState = hazeState)
        }

        AddToFavoritesButton(
            modifier = Modifier.align(Alignment.TopEnd),
            viewModel = viewModel,
            course = courseInfo
        )
    }
}

@Composable
private fun LoadingBar(
    modifier: Modifier = Modifier,
    visibility: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = visibility.value,
        modifier = modifier
    ) {
        CircularProgressIndicator(color = Green)
    }
}

@Composable
private fun AddToFavoritesButton(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel,
    course: Course
) {
    val likeState = remember { mutableStateOf(course.hasLike) }
    val tint = remember {
        derivedStateOf {
            when (likeState.value) {
                true -> Green
                false -> White
            }
        }
    }

    IconButton(
        onClick = {
            if (likeState.value == true) {
                viewModel.deleteFromFavorites(course.id)
            } else {
                viewModel.saveToFavorites((course as CourseDbo).copy(hasLike = true))
            }

            likeState.value = !likeState.value
        },
        modifier = modifier
            .padding(8.dp)
            .background(
                SolidColor(Gray), CircleShape, 0.4f
            )
            .size(32.dp)
    ) {
        Icon(
            painterResource(R.drawable.ic_bookmark),
            null,
            tint = tint.value
        )
    }
}

@Composable
private fun ToDescriptionButton(
    modifier: Modifier = Modifier,
    onNavigateTo: () -> Unit
) {
    TextButton(
        onClick = onNavigateTo,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.more),
            style = ButtonSmallType,
            color = Green,
            modifier = Modifier.padding(end = 4.dp)
        )
        Icon(
            painter = painterResource(R.drawable.ic_arrow),
            null,
            tint = Green
        )
    }
}

@Composable
private fun Rate(
    modifier: Modifier = Modifier,
    rate: String
) {
    TextButton(
        onClick = {},
        enabled = false,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_star),
            null,
            tint = Green
        )
        Text(
            text = rate,
            style = CaptionType,
            color = White,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
private fun Date(
    modifier: Modifier = Modifier,
    date: String,
    hazeState: HazeState
) {
    TextButton(
        onClick = {},
        enabled = false,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            disabledContainerColor = Transparent
        ),
        modifier = modifier.hazeEffect(hazeState) {
            backgroundColor = Gray
            tints = listOf(HazeTint(Gray))
            blurRadius = 30.dp
        }
    ) {
        Text(
            text = date,
            style = CaptionType,
            color = White,
            modifier = Modifier.padding(4.dp)
        )
    }
}