package com.llama.presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LLButton(
    modifier: Modifier = Modifier,
    text: String,
    isBtnCheck: Boolean = true,
    onClick:() -> Unit
) {
    Button(
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isBtnCheck) MaterialTheme.colorScheme.primary else Color.Gray,
            contentColor = if (isBtnCheck) MaterialTheme.colorScheme.onPrimary else Color.Gray
        ),
        onClick = {
            if (isBtnCheck) onClick()
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

