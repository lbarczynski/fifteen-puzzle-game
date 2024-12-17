package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.mokkery.answering.calls
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class BoardPreVerifierTest {

    private lateinit var boardPreVerifier: BoardPreVerifier

    @BeforeTest
    fun setUp() {
        boardPreVerifier = BoardVerifier()
    }

    @Test
    fun `GIVEN solvable 3x3 square board WHEN is solvable THEN return true`() {
        // given
        val board: Board = mockBoard(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 0, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        val isSolvable = boardPreVerifier.isSolvable(board)

        // then
        assertTrue(isSolvable)
    }

    @Test
    fun `GIVEN solvable 4x4 square board WHEN is solvable THEN return true`() {
        // given
        val board: Board = mockBoard(
            arrayOf(
                intArrayOf(1, 8, 2, 0),
                intArrayOf(4, 5, 6, 3),
                intArrayOf(7, 11, 10, 12),
                intArrayOf(9, 13, 14, 15)
            )
        )

        // when
        val isSolvable = boardPreVerifier.isSolvable(board)

        // then
        assertTrue(isSolvable)
    }

    @Test
    fun `GIVEN solvable 5x7 board WHEN is solvable THEN return true`() {
        // given
        val board: Board = mockBoard(
            arrayOf(
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                intArrayOf(8, 9, 10, 11, 12, 13, 14),
                intArrayOf(15, 16, 17, 18, 19, 20, 0),
                intArrayOf(21, 22, 23, 24, 25, 26, 27),
                intArrayOf(28, 29, 30, 31, 32, 33, 34)
            )
        )

        // when
        val isSolvable = boardPreVerifier.isSolvable(board)

        // then
        assertTrue(isSolvable)
    }

    @Test
    fun `GIVEN unsolvable 4x4 square board WHEN is solvable THEN return false`() {
        // given
        val board: Board = mockBoard(
            arrayOf(
                intArrayOf(3, 9, 1, 15),
                intArrayOf(14, 11, 4, 6),
                intArrayOf(13, 0, 10, 12),
                intArrayOf(2, 7, 8, 5),
            )
        )

        // when
        val isSolvable = boardPreVerifier.isSolvable(board)

        // then
        assertFalse(isSolvable)
    }

    private fun mockBoard(state: Array<IntArray>): Board {
        return mock {
            every { rows } returns state.size
            every { columns } returns state.first().size
            every { get(any(), any()) } calls { (row: Int, column: Int) -> state[row][column] }
        }
    }
}
