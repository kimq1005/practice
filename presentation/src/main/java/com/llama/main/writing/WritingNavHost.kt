package com.llama.main.writing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WritingNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WritingRoute.IMAGE_SELECTED_SCREEN.route,
     ) {
        composable(
            route = WritingRoute.IMAGE_SELECTED_SCREEN.route
        ) {

        }

        composable(
            route = WritingRoute.IMAGE_SELECTED_SCREEN.route
        ) {

        }
    }
}