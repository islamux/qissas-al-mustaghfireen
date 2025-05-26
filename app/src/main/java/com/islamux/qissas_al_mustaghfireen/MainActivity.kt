package com.islamux.qissas_al_mustaghfireen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.islamux.qissas_al_mustaghfireen.ui.AppNavHost
import com.islamux.qissas_al_mustaghfireen.ui.theme.QissasAlMustaghfireenTheme
// StoryViewModel is implicitly used by AppNavHost via viewModel() delegate, no explicit import needed here
// unless used for other purposes directly in MainActivity.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Keep this if it's part of the current project setup
        setContent {
            QissasAlMustaghfireenTheme {
                Surface( // Using Surface as a general container, similar to previous setup
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    // AppNavHost will internally get StoryViewModel using viewModel()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

// The Greeting and GreetingPreview are no longer used by MainActivity.
// They can be removed or kept for other testing/preview purposes if desired.
// For this task, we'll remove them to keep MainActivity clean.
// @Composable
// fun Greeting(name: String, modifier: Modifier = Modifier) {
// Text(
// text = "Hello $name!",
// modifier = modifier
// )
// }
//
// @Preview(showBackground = true)
// @Composable
// fun GreetingPreview() {
// QissasAlMustaghfireenTheme {
// Greeting("Android")
// }
// }