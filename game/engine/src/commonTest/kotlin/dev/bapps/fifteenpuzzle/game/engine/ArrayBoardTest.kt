package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ArrayBoardTest {

    @Test
    fun `GIVEN board that can be moved up WHEN can move up THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(0, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Up)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN board that can not be moved up WHEN can move up THEN should return false`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(0, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(1, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Up)

        // then
        assertFalse(result)
    }

    @Test
    fun `GIVEN board that can be moved down WHEN can move down THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 0),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Down)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN board that can not be moved down WHEN can move down THEN should return false`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 0, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Down)

        // then
        assertFalse(result)
    }

    @Test
    fun `GIVEN board that can be moved left WHEN can move left THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 0),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Left)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN board that can not be moved left WHEN can move left THEN should return false`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(0, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Left)

        // then
        assertFalse(result)
    }

    @Test
    fun `GIVEN board that can be moved right WHEN can move right THEN should return true`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 0, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        val result = board.canMove(Direction.Right)

        // then
        assertTrue(result)
    }

    @Test
    fun `GIVEN board that can not be moved right WHEN can move right THEN should return false`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 0)
            )
        )

        // when
        val result = board.canMove(Direction.Right)

        // then
        assertFalse(result)
    }

    @Test
    fun `GIVEN board that can be moved up WHEN move up THEN should update board state`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(0, 7, 8)
            )
        )

        // when
        board.move(Direction.Up)

        // then
        val expectedState: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(0, 5, 6),
                intArrayOf(4, 7, 8)
            )
        )
        assertEquals(expectedState, board)
    }

    @Test
    fun `GIVEN board that can not be moved up WHEN move up THEN should throw exception`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(0, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(1, 7, 8)
            )
        )

        // when / then
        assertFailsWith(IllegalArgumentException::class) { board.move(Direction.Up) }
    }

    @Test
    fun `GIVEN board that can be moved down WHEN move down THEN should update board state`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 0),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        board.move(Direction.Down)

        // then
        val expectedState: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 5),
                intArrayOf(3, 4, 0),
                intArrayOf(6, 7, 8)
            )
        )
        assertEquals(expectedState, board)
    }

    @Test
    fun `GIVEN board that can not be moved down WHEN move down THEN should throw exception`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 0, 8)
            )
        )

        // when / then
        assertFailsWith(IllegalArgumentException::class) { board.move(Direction.Down) }
    }

    @Test
    fun `GIVEN board that can be moved left WHEN move left THEN should update board state`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 0),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        board.move(Direction.Left)

        // then
        val expectedState: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 0, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )
        assertEquals(expectedState, board)
    }

    @Test
    fun `GIVEN board that can not be moved left WHEN move left THEN should throw exception`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(0, 7, 8)
            )
        )

        // when / then
        assertFailsWith(IllegalArgumentException::class) { board.move(Direction.Left) }
    }

    @Test
    fun `GIVEN board that can be moved right WHEN move right THEN should update board state`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 0, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )

        // when
        board.move(Direction.Right)

        // then
        val expectedState: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 0),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8)
            )
        )
        assertEquals(expectedState, board)
    }

    @Test
    fun `GIVEN board that can not be moved right WHEN move right THEN should throw exception`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 0)
            )
        )

        // when / then
        assertFailsWith(IllegalArgumentException::class) { board.move(Direction.Right) }
    }

    @Test
    fun `GIVEN board WHEN get element THEN should return proper value`() {
        // given
        val boardState = arrayOf(
            intArrayOf(8, 5, 2),
            intArrayOf(7, 4, 1),
            intArrayOf(6, 3, 0)
        )
        val board: Board = ArrayBoard(boardState)

        // when
        val values = Array(boardState.size) { row ->
            IntArray(boardState[row].size) { column ->
                board[row, column]
            }
        }

        // then
        assertEquals(boardState.size, values.size)
        for (row in boardState.indices)
            assertContentEquals(boardState[row], values[row])
    }

    @Test
    fun `GIVEN board WHEN get row and columns THEN should return proper values`() {
        // given
        val boardState = arrayOf(
            intArrayOf(8, 5, 2, 9),
            intArrayOf(7, 4, 1, 10),
            intArrayOf(6, 3, 0, 11)
        )
        val board: Board = ArrayBoard(boardState)

        // when
        val rows = board.rows
        val columns = board.columns

        // then
        assertEquals(boardState.size, rows)
        assertEquals(boardState.first().size, columns)
    }

    @Test
    fun `GIVEN board WHEN copy THEN return deep board copy`() {
        // given
        val board: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 0)
            )
        )

        // when
        val result = board.copy()
        board.move(Direction.Up)

        // then
        val expectedResult: Board = ArrayBoard(
            board = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 0)
            )
        )
        assertEquals(expectedResult, result)
    }
}
