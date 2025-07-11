package com.llama.presentation.main.board.comment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun CommentDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {

        }
    }
}

@Preview
@Composable
private fun CommentDialogPreview() {
    ConnectedTheme {
        CommentDialog(visible = false, onDismissRequest = {})
    }
}