package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed // Changed to itemsIndexed for animation delay
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.islamux.qissas_al_mustaghfireen.model.Story
import com.islamux.qissas_al_mustaghfireen.ui.theme.OceanCyan
import com.islamux.qissas_al_mustaghfireen.ui.theme.OceanDarkTeal
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetOrange
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetPink
import com.islamux.qissas_al_mustaghfireen.ui.theme.SunsetYellow
import com.islamux.qissas_al_mustaghfireen.viewmodel.StoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    storyViewModel: StoryViewModel,
    onNavigateToStory: (Int) -> Unit
) {
    val stories by storyViewModel.stories.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient( // This is the main screen background gradient
                    colors = listOf(
                        OceanDarkTeal.copy(alpha = 0.8f), // Make screen background slightly less intense
                        OceanCyan.copy(alpha = 0.6f)
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
                                    OceanDarkTeal,
                                    OceanCyan
                                )
                            )
                        )
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = "قصص المستغفرين",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge
                                // Color will be set by titleContentColor
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent, // TopAppBar itself is transparent
                            titleContentColor = Color.White     // Text color on gradient
                        )
                    )
                }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                .padding(horizontal = 8.dp), // Keep horizontal padding for screen edges
            verticalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between items
        ) {
            itemsIndexed(stories, key = { _, story -> story.id }) { index, story ->
                val visibleState = remember { MutableTransitionState(false) }
                visibleState.targetState = true // Animate on first composition

                AnimatedVisibility(
                    visibleState = visibleState,
                    enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = index * 100)) +
                            slideInVertically(
                                initialOffsetY = { it / 2 },
                                animationSpec = tween(durationMillis = 500, delayMillis = index * 100)
                            ),
                    // No exit animation defined for simplicity, items will just disappear if list changes
                ) {
                    StoryListItem(story = story, onItemClick = { onNavigateToStory(story.id) })
                }
            }
        }
    }
}

@Composable
fun StoryListItem(story: Story, onItemClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(targetValue = if (pressed) 0.98f else 1f, label = "cardScale")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale) // Apply scale animation
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable default ripple, using scale instead
                onClick = onItemClick
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp, // Slightly increased elevation for gradient cards
            pressedElevation = 6.dp,
            hoveredElevation = 6.dp
        )
        // colors will be handled by a Box with background gradient
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient( // Diagonal gradient
                        colors = listOf(SunsetOrange, SunsetPink, SunsetYellow),
                        // start = Offset(0f, Float.POSITIVE_INFINITY), // bottom-left
                        // end = Offset(Float.POSITIVE_INFINITY, 0f)    // top-right
                        // Default diagonal is top-start to bottom-end, which works fine.
                    )
                )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = story.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black, // Ensure contrast on Sunset gradient
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = story.excerpt,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black, // Ensure contrast on Sunset gradient
                    maxLines = 3,
                    // TextAlign.Start is default for Arabic text based on content
                )
            }
        }
    }
}
