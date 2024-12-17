package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction
import dev.bapps.fifteenpuzzle.game.api.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FifteenPuzzleGame internal constructor(initialBoardState: Board) : Game {

    private val moveMutex = Mutex()
    private val internalBoard = MutableStateFlow(initialBoardState)

    override val state: StateFlow<Board> = internalBoard.asStateFlow()

    override suspend fun move(direction: Direction): Boolean = moveMutex.withLock {
        val currentState = internalBoard.value
        if (!currentState.canMove(direction)) return false
        val updatedState = currentState.copy()
            .apply { move(direction) }
        internalBoard.emit(updatedState)
        return true
    }
}
