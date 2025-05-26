package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islamux.qissas_al_mustaghfireen.ui.theme.QissasAlMustaghfireenTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2500L) // 2.5 seconds delay
        onTimeout()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
                color = MaterialTheme.colorScheme.onBackground
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
