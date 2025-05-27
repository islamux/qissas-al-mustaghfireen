package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islamux.qissas_al_mustaghfireen.ui.theme.QissasAlMustaghfireenTheme
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetOrange
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetPink
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetYellow
import kotlinx.coroutines.delay
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2500L) // 2.5 seconds delay
        onTimeout()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "splash_gradient_transition")
    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "splash_alpha_animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SunsetOrange,
                        SunsetPink.copy(alpha = animatedAlpha), // Apply animated alpha
                        SunsetYellow
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "قصص المستغفرين",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = Color.White // Changed text color for contrast
            )
            // Optionally, add an icon here later if desired
            // For now, text only as per instructions
        }
    }
}

@Preview(showBackground = true, name = "Splash Screen Light")
@Composable
fun SplashScreenPreviewLight() {
    QissasAlMustaghfireenTheme(darkTheme = false) {
        SplashScreen(onTimeout = {})
    }
}

@Preview(showBackground = true, name = "Splash Screen Dark")
@Composable
fun SplashScreenPreviewDark() {
    QissasAlMustaghfireenTheme(darkTheme = true) {
        SplashScreen(onTimeout = {})
    }
}
