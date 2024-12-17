package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction
import dev.bapps.fifteenpuzzle.game.api.Game
import dev.bapps.fifteenpuzzle.game.engine.board.ArrayBoardGenerator
import dev.bapps.fifteenpuzzle.game.engine.board.BoardGenerator
import dev.bapps.fifteenpuzzle.game.engine.board.BoardPreVerifier
import dev.bapps.fifteenpuzzle.game.engine.board.BoardVerifier
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

    class Generator internal constructor(
        private val boardGenerator: BoardGenerator,
        private val boardPreVerifier: BoardPreVerifier
    ) : Game.Generator {

        constructor() : this(
            boardGenerator = ArrayBoardGenerator(),
            boardPreVerifier = BoardVerifier()
        )

        override fun createNewGame(rows: Int, columns: Int): Game {
            while (true) {
                val board = boardGenerator.random(rows, columns)
                if (boardPreVerifier.isSolvable(board)) {
                    return FifteenPuzzleGame(board)
                }
            }
        }
    }
}
