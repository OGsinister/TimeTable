package com.example.timetable.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.text,
    surface =  ThemeColors.Night.surface,
    background = ThemeColors.Night.background,
    onSecondary = ThemeColors.Night.onSecondary,
    onSurface = ThemeColors.Night.calendarSelectedItem
)

private val LightColorPalette = lightColors(
    primary = ThemeColors.Light.primary,
    onPrimary = ThemeColors.Light.text,
    surface =  ThemeColors.Light.surface,
    background = ThemeColors.Light.background,
    onSecondary = ThemeColors.Light.onSecondary,
    onSurface = ThemeColors.Light.calendarSelectedItem

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
fun TimeTableTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(isDarkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}