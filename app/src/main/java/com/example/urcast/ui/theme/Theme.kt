package com.example.urcast.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF176B70),
    onPrimary = Color.White,
    secondary = Color(0xFFD5F0EE),
    background = Color(0xFFF4F8FA),
    surface = Color.White,
    onSurface = Color(0xFF193238)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF80CBC4),
    onPrimary = Color(0xFF003735),
    secondary = Color(0xFFB2DFDB),
    background = Color(0xFF14252D),
    surface = Color(0xFF20343D),
    onSurface = Color(0xFFE5F1F3)
)

private val HighContrastLightColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    secondary = Color(0xFFE0E0E0),
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
)

private val HighContrastDarkColorScheme = darkColorScheme(
    primary = Color.Yellow,
    onPrimary = Color.Black,
    secondary = Color(0xFFBDBDBD),
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    error = Color(0xFFFF6B6B),
    onError = Color.Black
)

@Composable
fun UrcastTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    fontSize: String = "Medium",
    highContrast: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        highContrast && darkTheme -> HighContrastDarkColorScheme
        highContrast -> HighContrastLightColorScheme

        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography(fontSize),
        content = content
    )
}