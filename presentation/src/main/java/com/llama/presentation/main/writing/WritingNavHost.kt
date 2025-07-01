package com.llama.presentation.main.writing

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WritingNavHost(
    onFinish: () -> Unit,
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val sharedViewModel: WritingViewModel = viewModel()

    sharedViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is WritingSideEffect.Finish -> onFinish()
            is WritingSideEffect.Toast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = WritingRoute.IMAGE_SELECTED_SCREEN.route,
     ) {
        composable(
            route = WritingRoute.IMAGE_SELECTED_SCREEN.route
        ) {
            ImageSelectSuccessScreen(
                viewModel = sharedViewModel,
                onNextClick = { navController.navigate(WritingRoute.WRITING_SCREEN.route) },
                onBackClick = onFinish
            )
        }

        composable(
            route = WritingRoute.WRITING_SCREEN.route
        ) {
            WritingSuccessScreen(
                viewModel = sharedViewModel,
                onBackClick = { navController.navigateUp() },
                onPostClick = sharedViewModel::onPostClick
            )
        }
    }
}