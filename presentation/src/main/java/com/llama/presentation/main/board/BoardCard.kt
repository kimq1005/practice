package com.llama.presentation.main.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.llama.presentation.component.LLImagePager
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun BoardCard(
    profileImageUrl: String? = null,
    username: String,
    images: List<String>,
    text: String,
    onOptionClick: () -> Unit,
    onReplyClick: () -> Unit,
) {
    var commentDialogVisible by remember { mutableStateOf(false) }

    Surface {
        Column(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
        ) {
            BoardHeader(
                modifier = Modifier.fillMaxWidth(),
                profileImageUrl = profileImageUrl,
                username = username,
                onOptionClick = onOptionClick
            )

            if (images.isNotEmpty()) {
                LLImagePager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    images = images,
                )
            }

            var maxLines by remember(text) { mutableIntStateOf(1) }
            var showMore by remember { mutableStateOf(false) }

            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = text
            )

            if (showMore) {
                TextButton(
                    onClick = {
                        maxLines = Integer.MAX_VALUE
                    }
                ) {
                    Text(
                        text = "더보기",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            // 댓글버튼
            TextButton(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.End),
                onClick = { commentDialogVisible = true }
            ) {
                Text(text = "댓글")
            }
        }
    }
}

@Preview
@Composable
private fun BoardCardPreview() {
    ConnectedTheme {
        BoardCard(
            profileImageUrl = null,
            username = "Fast Campus",
            images = listOf("asdadadsadaq"),
            text = "asdasdasdasdasdadasd",
            onOptionClick = {},
            onReplyClick = {}
        )
    }
}