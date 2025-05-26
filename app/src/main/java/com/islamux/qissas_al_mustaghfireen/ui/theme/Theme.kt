package com.islamux.qissas_al_mustaghfireen.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = Color.Black, // On a lighter teal
    primaryContainer = DarkPrimary.copy(alpha = 0.3f), // Example: a more desaturated/lighter container
    onPrimaryContainer = Color.White,

    secondary = DarkSecondary,
    onSecondary = Color.White, // On a darker tan
    secondaryContainer = DarkSecondary.copy(alpha = 0.3f),
    onSecondaryContainer = Color.White,

    tertiary = DarkTertiary,
    onTertiary = Color.Black, // On a pale gold
    tertiaryContainer = DarkTertiary.copy(alpha = 0.3f),
    onTertiaryContainer = Color.Black,

    error = DarkError,
    onError = Color.Black, // On a lighter red
    errorContainer = DarkError.copy(alpha = 0.3f),
    onErrorContainer = Color.Black,

    background = DarkBackground,
    onBackground = Color.White,

    surface = DarkSurface,
    onSurface = Color.White,

    surfaceVariant = DarkSurface.copy(alpha = 0.6f), // Example: slightly different shade of surface
    onSurfaceVariant = Color.White, // Lighter text on darker surface variant

    outline = DarkSecondary.copy(alpha = 0.5f) // Example: an outline color
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = Color.White, // On a medium-dark teal
    primaryContainer = LightPrimary.copy(alpha = 0.2f), // Example: a lighter container
    onPrimaryContainer = Color.Black, // Darker text on lighter container

    secondary = LightSecondary,
    onSecondary = Color.Black, // On a light tan
    secondaryContainer = LightSecondary.copy(alpha = 0.4f),
    onSecondaryContainer = Color.Black,

    tertiary = LightTertiary,
    onTertiary = Color.Black, // On a muted gold
    tertiaryContainer = LightTertiary.copy(alpha = 0.3f),
    onTertiaryContainer = Color.Black,

    error = LightError,
    onError = Color.White, // On a standard red
    errorContainer = LightError.copy(alpha = 0.2f),
    onErrorContainer = Color.Black,

    background = LightBackground,
    onBackground = Color.Black,

    surface = LightSurface,
    onSurface = Color.Black,

    surfaceVariant = LightBackground.copy(alpha = 0.8f), // Example: slightly different shade of background/surface
    onSurfaceVariant = Color.Black, // Darker text on lighter surface variant

    outline = LightSecondary.copy(alpha = 0.5f) // Example: an outline color
)

@Composable
fun QissasAlMustaghfireenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}