package dev.bapps.fifteenpuzzle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bapps.fifteenpuzzle.feature.game.GameScreen

@Composable
fun Application(modifier: Modifier = Modifier) {
    MaterialTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.primarySurface)
        ) {
            GameScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
