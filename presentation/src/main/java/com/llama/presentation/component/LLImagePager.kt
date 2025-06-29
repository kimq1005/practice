package com.llama.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.llama.presentation.theme.ConnectedTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LLImagePager(
    modifier: Modifier = Modifier,
    images: List<String>,
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
        ) { index ->
            val image = images[index]

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(model = image),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.TopEnd)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                text = "${(pagerState.currentPage + 1)}/${images.size}",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun LLImagePagerPreview() {
    ConnectedTheme {
        LLImagePager(
            images = emptyList()
        )
    }
}