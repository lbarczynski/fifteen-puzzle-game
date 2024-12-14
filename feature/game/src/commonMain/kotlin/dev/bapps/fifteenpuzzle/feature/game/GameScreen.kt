package dev.bapps.fifteenpuzzle.feature.game

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.bapps.fifteenpuzzle.game.ui.GameBoard

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    Box(modifier) {
        GameBoard()
    }
}
