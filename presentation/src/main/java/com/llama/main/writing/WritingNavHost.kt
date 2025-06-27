package com.llama.main.writing

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WritingNavHost(
    onFinish: () -> Unit,
) {
    val navController = rememberNavController()
    val sharedViewModel: WritingViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = WritingRoute.IMAGE_SELECTED_SCREEN.route,
     ) {
        composable(
            route = WritingRoute.IMAGE_SELECTED_SCREEN.route
        ) {
            ImageSelectSuccessScreen(
                viewModel = sharedViewModel,
                onBackClick = onFinish
            )
        }

        composable(
            route = WritingRoute.WRITING_SCREEN.route
        ) {

        }
    }
}