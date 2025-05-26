package com.islamux.qissas_al_mustaghfireen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle( // Used on SplashScreen
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold, // Slightly bolder for emphasis
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp // Adjusted for Arabic
    ),
    titleLarge = TextStyle( // Used for TopAppBar titles
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal, // Standard M3 is Normal, could be Medium
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp // Adjusted for Arabic
    ),
    titleMedium = TextStyle( // Used for StoryListItem title
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium, // Good for item titles
        fontSize = 18.sp, // Slightly larger for item titles
        lineHeight = 24.sp,
        letterSpacing = 0.sp // Adjusted for Arabic
    ),
    bodyLarge = TextStyle( // Used for StoryDetailScreen full text
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp // Adjusted from 0.5.sp for Arabic
    ),
    bodyMedium = TextStyle( // Used for StoryListItem excerpt
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp // Adjusted for Arabic
    ),
    labelSmall = TextStyle( // Example if needed for smaller labels
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.1.sp // Small letter spacing can be okay for labels
    )
)