package ru.effectivemobile.authorization_impl.presentation.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.effectivemobile.authorization_impl.R
import ru.effectivemobile.authorization_impl.presentation.view.theme.Blue
import ru.effectivemobile.authorization_impl.presentation.view.theme.ButtonType
import ru.effectivemobile.authorization_impl.presentation.view.theme.Green
import ru.effectivemobile.authorization_impl.presentation.view.theme.LightGray
import ru.effectivemobile.authorization_impl.presentation.view.theme.LightOrange
import ru.effectivemobile.authorization_impl.presentation.view.theme.Orange
import ru.effectivemobile.authorization_impl.presentation.view.theme.Transparent
import ru.effectivemobile.authorization_impl.presentation.view.theme.Typography
import ru.effectivemobile.authorization_impl.presentation.view.theme.White
import ru.effectivemobile.authorization_impl.presentation.viewmodel.AuthViewModel

@Composable
internal fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = AuthViewModel(),
    onNavigateTo: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isEmailCorrect = remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.login),
            style = Typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Text(
            text = stringResource(R.string.email),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        EmailInputField(
            email = email,
            isCorrect = isEmailCorrect,
            viewModel = viewModel
        )

        Text(
            text = stringResource(R.string.password),
            style = Typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        PasswordInputField(password = password)

        LoginButton(
            email = email,
            password = password,
            isCorrect = isEmailCorrect,
            viewModel = viewModel,
            onNavigateTo = onNavigateTo
        )

        BottomText(modifier = Modifier.align(Alignment.CenterHorizontally))

        DividingLine()

        SocialNetworkButtons(viewModel = viewModel)
    }
}

@Composable
private fun EmailInputField(
    email: MutableState<String>,
    isCorrect: MutableState<Boolean>,
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    TextField(
        value = email.value,
        onValueChange = {
            email.value = it

            isCorrect.value = email.value.isNotBlank() && viewModel.isValidEmail(email.value)
        },
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = LightGray,
            cursorColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        isError = !isCorrect.value && email.value.isNotEmpty(),
        placeholder = { LabelText(stringResource(R.string.email_example)) },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
private fun PasswordInputField(
    password: MutableState<String>,
    modifier: Modifier = Modifier
) {
    TextField(
        value = password.value,
        onValueChange = { newText ->
            password.value = newText.filter { it.isLatin() }
        },
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = LightGray,
            cursorColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        placeholder = { LabelText(stringResource(R.string.enter_password)) },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}

@Composable
private fun LoginButton(
    modifier: Modifier = Modifier,
    email: MutableState<String>,
    password: MutableState<String>,
    isCorrect: MutableState<Boolean>,
    viewModel: AuthViewModel,
    onNavigateTo: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Button(
        onClick = {
            scope.launch {
                val result = viewModel.logIn(
                    email.value,
                    password.value
                )
                if (result && isCorrect.value) {
                    viewModel.saveAuthState(context)
                    onNavigateTo()
                } else {
                    Toast.makeText(context, R.string.wrong_log_pass, Toast.LENGTH_LONG).show()
                }
            }
        },
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Text(text = stringResource(R.string.login), style = ButtonType)
    }
}

@Composable
private fun LabelText(text: String, modifier: Modifier = Modifier) {
    Text(text = text, style = Typography.bodySmall, modifier = modifier)
}

@Composable
private fun BottomText(modifier: Modifier = Modifier) {

    val regString = buildAnnotatedString {
        append(stringResource(R.string.no_acc))
        append(" ")
        withLink(
            LinkAnnotation.Clickable(
                tag = stringResource(R.string.registration),
                linkInteractionListener = {
                    //ссылка на браузер для регистрации
                }
            )
        ) {
            withStyle(style = SpanStyle(color = Green)) {
                append(stringResource(R.string.registration))
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = regString, style = ButtonType, fontSize = 11.sp)
        TextButton(
            onClick = {
                //Здесь действие по сбросу пароля
            }
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                style = ButtonType,
                color = Green,
                textAlign = TextAlign.Center,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun DividingLine(modifier: Modifier = Modifier) {
    HorizontalDivider(
        color = LightGray,
        thickness = 1.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
private fun SocialNetworkButtons(
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.padding(top = 16.dp)
    ) {
        VkButton(context, viewModel, Modifier.weight(1f))
        OkButton(context, viewModel, Modifier.weight(1f))
    }
}

@Composable
private fun VkButton(
    context: Context,
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            viewModel.openVk(context)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
            .background( // Приходится делать это и тут, для синхронизации дизайна двух кнопок
                color = Blue,
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        Image(painterResource(R.drawable.ic_vk), contentDescription = "")
    }
}

@Composable
private fun OkButton(
    context: Context,
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    //А почему только эта кнопка градиентальная?
    Button(
        onClick = {
            viewModel.openOk(context)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(LightOrange, Orange)
                ),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(start = 8.dp)
    ) {
        Image(
            painterResource(R.drawable.ic_ok),
            contentDescription = ""
        )
    }
}

private fun Char.isLatin(): Boolean {
    return this in 'a'..'z' || this in 'A'..'Z'
}