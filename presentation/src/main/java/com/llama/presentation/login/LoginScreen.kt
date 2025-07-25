package com.llama.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.llama.presentation.component.LLButton
import com.llama.presentation.component.LLTextField
import com.llama.presentation.main.MainActivity
import com.llama.presentation.theme.ConnectedTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUpScreen: () -> Unit
) {
    val state = viewModel.collectAsState().value
    val context = LocalContext.current

    viewModel.collectSideEffect { sideEffect ->
        when(sideEffect) {
            is LoginSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            is LoginSideEffect.NavigateToMainActivity -> {
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }

                context.startActivity(intent)
            }
        }
    }

    LoginScreen(
        id = state.id,
        password = state.password,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onNavigateToSignUpScreen = onNavigateToSignUpScreen,
        onLoginClick = viewModel::onLoginClick
    )
}


@Composable
private fun LoginScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToSignUpScreen: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Connected",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = "Your favorite social network",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 36.dp),
                    text = "Log in",
                    style =  MaterialTheme.typography.headlineMedium
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = "Id",
                    style =  MaterialTheme.typography.labelLarge
                )

                LLTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = id,
                    onValueString = onIdChange
                )

                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = "password",
                    style =  MaterialTheme.typography.labelLarge
                )

                LLTextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = password,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueString = onPasswordChange
                )

                LLButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                        .height(48.dp),
                    text = "로그인",
                    onClick = onLoginClick
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 24.dp)
                        .clickable(onClick = onNavigateToSignUpScreen)
                ) {
                    Text(text = "Don't have an account")
                    Text(text = "Sign up", color = MaterialTheme.colorScheme.primary)
                }

            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    ConnectedTheme {
        LoginScreen(
            id = "hendrerit",
            password = "quam",
            onIdChange = {},
            onPasswordChange = {},
            onNavigateToSignUpScreen = {},
            onLoginClick = {}
        )
    }
}