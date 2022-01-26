package com.example.composebasicslab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White

val Navy = Color(0xFF073042)
val Blue = Color(0xFF04285F)
val LightBlue = Color(0xFF4ABAEB)
val BackgroundCol = Color(0xFFEFF7CF)

private val DarkColorPalette = darkColors(
    primary = Navy,
//    primaryVariant = Purple700,
//    secondary = Teal200,
    onSurface = Navy,
    onPrimary = BackgroundCol,
    surface = Blue
)

private val LightColorPalette = lightColors(
    primary = LightBlue,
//    primaryVariant = Purple700,
//    secondary = Teal200,
    onSurface = White,
    onPrimary = Navy,
    surface = Blue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposeBasicsLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}