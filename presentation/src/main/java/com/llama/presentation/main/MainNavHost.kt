package com.llama.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.llama.presentation.main.board.BoardSuccessScreen
import com.llama.presentation.main.setting.SettingScreen
import com.llama.presentation.main.test.TestSuccessScreen
import com.llama.presentation.R
import com.llama.presentation.main.board.BoardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    boardViewModel: BoardViewModel
) {
    val navController = rememberNavController()

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                    title = {
                        Text(text = stringResource(R.string.app_name))
                    })
            },
            content = { contentPadding ->
                NavHost(
                    modifier = Modifier
                        .padding(contentPadding),
                    navController = navController,
                    startDestination = MainRoute.BOARD.route
                ) {
                    composable(route = MainRoute.BOARD.route) {
                        BoardSuccessScreen(
                            viewModel = boardViewModel
                        )
                    }

                    composable(route = MainRoute.SETTING.route) {
                        SettingScreen()
                    }

                    composable(route = MainRoute.TESTING.route) {
                        TestSuccessScreen()
                    }
                }
            },
            bottomBar = {
                MainBottomBar(
                    navController = navController
                )
            }
        )
    }
}