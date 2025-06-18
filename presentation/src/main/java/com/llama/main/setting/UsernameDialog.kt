package com.llama.main.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.llama.presentation.theme.ConnectedTheme

@Composable
fun UsernameDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    initialUsername: String,
    onUsernameChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            var username by remember { mutableStateOf(initialUsername) }

            Surface {
                Column(
                    modifier = modifier
                        .fillMaxWidth(0.8f)
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    )

                    Row {
                        TextButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = {
                                onUsernameChange(username)
                                onDismissRequest()
                            }
                        ) {
                            Text(text = "변경")
                        }

                        TextButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = onDismissRequest
                        ) {
                            Text(text = "취소")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUsernameDialog() {
    ConnectedTheme {
        UsernameDialog(
            visible = true,
            initialUsername = "초기 닉네임",
            onUsernameChange = {},
            onDismissRequest = {},
        )
    }
}