package dev.bapps.fifteenpuzzle.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GameBoard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("Game board")
    }
}
