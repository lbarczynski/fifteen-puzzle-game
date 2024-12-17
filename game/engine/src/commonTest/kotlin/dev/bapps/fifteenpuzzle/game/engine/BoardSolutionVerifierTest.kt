package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class BoardSolutionVerifierTest {

    private lateinit var boardSolutionVerifier: BoardSolutionVerifier

    @BeforeTest
    fun setup() {
        boardSolutionVerifier = BoardVerifier()
    }

    @Test
    fun `GIVEN solved square board WHEN is solved THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12),
                intArrayOf(13, 14, 15, 0)
            )
        )

        // when
        val result = boardSolutionVerifier.isSolved(board)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN solved rectangle board WHEN is solved THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(6, 7, 8, 9, 0)
            )
        )

        // when
        val result = boardSolutionVerifier.isSolved(board)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN not solved board WHEN is solved THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 0, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        val result = boardSolutionVerifier.isSolved(board)

        // then
        assertFalse(result)
    }
}
