package dev.bapps.fifteenpuzzle.game.api

import kotlinx.coroutines.flow.StateFlow

interface Game {
    val state: StateFlow<Board>
    suspend fun move(direction: Direction): Boolean

    interface Generator {
        fun createNewGame(rows: Int, columns: Int): Game
    }
}
