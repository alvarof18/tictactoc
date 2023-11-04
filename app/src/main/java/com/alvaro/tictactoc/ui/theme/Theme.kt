package com.alvaro.tictactoc.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = PurpleGrey80,

    )

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TicTacTocTheme(
       content: @Composable () -> Unit
) {

    MaterialTheme(colors = LightColorScheme, typography = Typography, content = content)
}