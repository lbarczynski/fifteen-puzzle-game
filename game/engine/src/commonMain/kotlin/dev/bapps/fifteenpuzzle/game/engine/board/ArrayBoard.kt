package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction

internal class ArrayBoard private constructor(
    private val board: Array<IntArray>,
    private var emptySlot: Pair<Int, Int>
) : Board {

    constructor(board: Array<IntArray>) : this(
        board = board,
        emptySlot = findZero(board)
    )

    override val rows: Int = board.size
    override val columns: Int = board.first().size

    override fun get(row: Int, column: Int): Int {
        return board[row][column]
    }

    override fun canMove(direction: Direction): Boolean {
        return when (direction) {
            Direction.Up -> emptySlot.first > 0
            Direction.Down -> emptySlot.first < board.lastIndex
            Direction.Left -> emptySlot.second > 0
            Direction.Right -> emptySlot.second < board.first().lastIndex
        }
    }

    override fun move(direction: Direction) {
        require(canMove(direction)) { "Move \"$direction\" is not possible for current board state!" }
        val destination = when (direction) {
            Direction.Up -> emptySlot.first - 1 to emptySlot.second
            Direction.Down -> emptySlot.first + 1 to emptySlot.second
            Direction.Left -> emptySlot.first to emptySlot.second - 1
            Direction.Right -> emptySlot.first to emptySlot.second + 1
        }
        swap(emptySlot, destination)
        emptySlot = destination
    }

    override fun isSolved(): Boolean {
        val maxValue = rows * columns
        var expectedValue = 1
        for (row in 0 until rows) {
            for (column in 0 until columns) {
                if (this[row, column] != expectedValue % maxValue) {
                    return false
                }
                ++expectedValue
            }
        }

        return true
    }

    override fun copy(): Board = ArrayBoard(
        board = Array(rows) { i -> board[i].copyOf() },
        emptySlot = emptySlot.copy()
    )

    private fun swap(source: Pair<Int, Int>, destination: Pair<Int, Int>) {
        val tmp = board[source.first][source.second]
        board[source.first][source.second] = board[destination.first][destination.second]
        board[destination.first][destination.second] = tmp
    }

    override fun hashCode(): Int {
        // todo : add multiplier to different board from array
        return board.contentDeepHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is ArrayBoard) return false
        return board contentDeepEquals other.board
    }

    override fun toString(): String {
        return board.joinToString(prefix = "[", postfix = "]") { column ->
            column.joinToString(prefix = "[", postfix = "]")
        }
    }

    companion object {
        private fun findZero(board: Array<IntArray>): Pair<Int, Int> {
            for (row in board.indices) {
                for (column in board[row].indices) {
                    if (board[row][column] == 0) {
                        return row to column
                    }
                }
            }
            error("Zero value not found on the board!")
        }
    }
}
