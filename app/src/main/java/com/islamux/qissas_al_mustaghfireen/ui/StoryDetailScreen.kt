package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
// import androidx.compose.foundation.lazy.items // No longer needed for single item
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.islamux.qissas_al_mustaghfireen.ui.theme.ForestEmerald
import com.islamux.qissas_al_mustaghfireen.ui.theme.ForestGreen
import com.islamux.qissas_al_mustaghfireen.viewmodel.StoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryDetailScreen(
    storyViewModel: StoryViewModel,
    onNavigateBack: () -> Unit
) {
    val selectedStory by storyViewModel.selectedStory.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient( // This is the main screen background gradient
                    colors = listOf(
                        ForestEmerald.copy(alpha = 0.8f), // Make screen background slightly less intense
                        ForestGreen.copy(alpha = 0.6f)
                    )
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent, // Make Scaffold background transparent
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient( // Gradient for the TopAppBar
                                colors = listOf(
                                    ForestEmerald,
                                    ForestGreen
                                )
                            )
                        )
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = selectedStory?.title ?: "Story Detail",
                                style = MaterialTheme.typography.titleLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                                // Color will be set by titleContentColor
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                    // Color will be set by navigationIconContentColor
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,      // TopAppBar itself is transparent
                            titleContentColor = Color.White,         // Text color on gradient
                            navigationIconContentColor = Color.White // Icon color on gradient
                        )
                    )
                }
            }
        ) { innerPadding ->
            if (selectedStory != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp, vertical = 8.dp) // Consistent padding
                ) {
                    item { // Display fullText in a single Text composable for better flow
                        Text(
                            text = selectedStory!!.fullText,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Start, // Or TextAlign.Justify
                            color = Color.White // Ensure text is readable on dark gradient
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Loading story...",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White // Ensure text is readable on dark gradient
                    )
                }
            }
        }
    }
}
