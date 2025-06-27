package com.llama.main.writing

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.llama.domain.model.Image
import com.llama.presentation.theme.ConnectedTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun ImageSelectSuccessScreen(
    viewModel: WritingViewModel,
    onBackClick: () -> Unit
) {
    val state = viewModel.collectAsState().value
    LaunchedEffect(Unit) {
        Log.d("TAG", "ImageSelectSuccessScreen: HI")
    }
    ImageSelectScreen(
        images = state.images,
        onNextClick = {},
        onBackClick = onBackClick
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageSelectScreen(
    images: List<Image> = emptyList(),
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
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
                            onClick = onNextClick
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
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxSize(),
                            painter = rememberAsyncImagePainter(
                                model = images.lastOrNull()?.uri
                            ),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }

                    LazyVerticalGrid(
                        modifier = Modifier
                            .weight(1f),
                        columns = GridCells.Adaptive(110.dp)
                    ) {
                        items(
                            count = images.size,
                            key =  { index -> images[index].uri }
                        ) { index ->
                            val image = images[index]

                            Box(
                                modifier = Modifier
                            ) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                    painter = rememberAsyncImagePainter(
                                        model = image.uri,
                                        contentScale = ContentScale.Crop
                                    ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun ImageSelectScreenPreview() {
    ConnectedTheme {
        ImageSelectScreen(
            onNextClick = {},
            onBackClick = {}
        )
    }
}