package com.islamux.qissas_al_mustaghfireen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.islamux.qissas_al_mustaghfireen.model.Story
import com.islamux.qissas_al_mustaghfireen.viewmodel.StoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    storyViewModel: StoryViewModel,
    onNavigateToStory: (Int) -> Unit
) {
    val stories by storyViewModel.stories.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "قصص المستغفرين",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        shape = RoundedCornerShape(12.dp), // Added rounded corners
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp, // Added pressed elevation
            hoveredElevation = 4.dp  // Added hovered elevation (for platforms that support it)
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = story.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 6.dp) // Increased bottom padding slightly
            )
            Text(
                text = story.excerpt,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3, // Show a snippet of the excerpt
                // TextAlign.Start is default for Arabic text based on content
            )
        }
    }
}
