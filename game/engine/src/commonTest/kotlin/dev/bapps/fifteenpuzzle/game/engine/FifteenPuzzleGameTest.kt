package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction
import dev.bapps.fifteenpuzzle.game.api.Game
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class FifteenPuzzleGameTest {

    @Test
    fun `GIVEN initialized game WHEN collect board value THEN should return initial board state`() {
        // given
        val initialState: Board = mock()
        val game: Game = FifteenPuzzleGame(initialState)

        // when
        val boardState = game.state.value

        // then
        assertEquals(initialState, boardState)
    }

    @Test
    fun `GIVEN possible move direction WHEN move THEN should update board and is solved state`() = runTest {
        // given
        val initialState: Board = mock()
        val updatedState: Board = mock()
        val game: Game = FifteenPuzzleGame(initialState)
        val moveDirection: Direction = Direction.Down
        every { initialState.canMove(moveDirection) } returns true
        every { initialState.copy() } returns updatedState
        every { updatedState.move(moveDirection) } returns Unit

        // when
        val operationResult = game.move(moveDirection)

        // then
        assertTrue(operationResult)
        assertEquals(updatedState, game.state.value)
    }

    @Test
    fun `GIVEN not possible move direction WHEN move THEN should return false`() = runTest {
        // given
        val initialState: Board = mock()
        val game: Game = FifteenPuzzleGame(initialState)
        val moveDirection: Direction = Direction.Down
        every { initialState.canMove(moveDirection) } returns false

        // when
        val operationResult = game.move(moveDirection)

        // then
        assertFalse(operationResult)
        assertEquals(initialState, game.state.value)
    }
}
