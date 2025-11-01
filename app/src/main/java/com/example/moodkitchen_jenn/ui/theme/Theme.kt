package com.example.moodkitchen_jenn.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = TealPrimary,
    secondary = OrangeSecondary,
    background = PeachBackground,
    surface = PeachBackground
    // You can add more colors like onPrimary, onSecondary if needed
)

@Composable
fun MoodKitchenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
