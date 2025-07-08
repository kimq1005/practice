package com.llama.presentation.main.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun TestSuccessScreen(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    TestScreen(
        sum = state.count,
        onDecrement = {
            viewModel.onEvent(TestEvent.Decrement)
        },
        onIncrement = {
            viewModel.onEvent(TestEvent.Increment)
        }
    )
}

@Composable
fun TestScreen(
    sum: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = sum.toString(),
                color = Color.White
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(
                    onClick = {
                        onIncrement()
                    }
                ) {
                    Text("증가")
                }

                TextButton(
                    onClick = {
                        onDecrement()
                    }
                ) {
                    Text("빼기")
                }
            }
        }
    }
}

@Preview
@Composable
private fun TestScreenPreview() {
    ConnectedTheme {
        TestScreen(
            sum = 1,
            onDecrement = {},
            onIncrement = {}
        )
    }
}