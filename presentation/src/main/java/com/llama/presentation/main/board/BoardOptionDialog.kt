package com.llama.presentation.main.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.llama.presentation.model.model.board.BoardCardModel
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun BoardOptionDialog(
    model: BoardCardModel?,
    onDismissRequest: () -> Unit,
    onBoardDelete: (BoardCardModel) -> Unit,
) {
    model?.run {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        onClick = {
                            onBoardDelete(this@run)
                            onDismissRequest()
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                    ) {
                        Text(
                            text = "제목"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BoardOptionDialogPreview() {
    ConnectedTheme {
        var visible by remember { mutableStateOf(true) }

        BoardOptionDialog(
            model = null,
            onBoardDelete = {},
            onDismissRequest = {
                visible = false
            }
        )
    }
}