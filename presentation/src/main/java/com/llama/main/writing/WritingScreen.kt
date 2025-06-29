package com.llama.main.writing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.llama.presentation.component.LLImagePager
import com.llama.presentation.component.LLTextField
import com.llama.presentation.theme.ConnectedTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun WritingSuccessScreen(
    viewModel: WritingViewModel,
    onPostClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val state = viewModel.collectAsState().value

    WritingScreen(
        images = state.selectedImage.map { it.uri },
        text = state.text,
        onTextChange = viewModel::onTextChange,
        onPostClick = onPostClick,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WritingScreen(
    images: List<String>,
    text: String,
    onTextChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onPostClick: () -> Unit,
) {
    Surface {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "새 게시물",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "뒤로가기"
                            )
                        }
                    },
                    actions = {
                        TextButton(
                            onClick = onPostClick
                        ) {
                            Text(text = "다음")
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    LLImagePager(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        images = images
                    )

                    Divider()

                    LLTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f),
                        value = text,
                        onValueString = onTextChange
                    )
                }
            })
    }
}


@Preview
@Composable
private fun WritingScreenPreview() {
    ConnectedTheme {
        WritingScreen(
            images = emptyList(),
            text = "",
            onTextChange = {},
            onBackClick = {},
            onPostClick = {}
        )
    }
}