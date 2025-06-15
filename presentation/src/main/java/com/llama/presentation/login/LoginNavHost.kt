package com.llama.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute.WelcomeScreen.name,
    ) {
        composable(route = LoginRoute.WelcomeScreen.name) {
            WelcomeScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginRoute.LoginScreen.name)
                }
            )
        }

        composable(route = LoginRoute.LoginScreen.name) {
            LoginScreen(
                onNavigateToSignUpScreen = {
                    navController.navigate(LoginRoute.SignUpScreen.name)
                }
            )
        }

        composable(route = LoginRoute.SignUpScreen.name) {
            SignUpScreen(
                id = "",
                username = "",
                password1 = "",
                password2 = "",
                onIdChange = {},
                onUsernameChange = {},
                onPassword1Change = {},
                onPassword2Change = {},
                onSignUpClick = {}
            )
        }
    }
}