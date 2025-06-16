package com.llama.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.llama.architecturepractice.ui.theme.Typography

private val ColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = Color.White,
    primaryContainer = primaryContainer,
    surface = Color.Black,
    onSurface = Color.White,
    background = background,
    onBackground = Color.White
)

@Composable
fun ConnectedTheme(
    content: @Composable () -> Unit
) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val colorScheme = ColorScheme

    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}