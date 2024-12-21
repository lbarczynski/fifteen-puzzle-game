package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Direction

internal class StringBoard private constructor(
    private val board: StringBuilder,
    private var emptySlotIndex: Int = board.indexOf('0'),
    override val rows: Int,
    override val columns: Int,
) : Board {

    constructor(board: Array<IntArray>) : this(
        board = StringBuilder(
            board
                .flatMap { row -> row.map(::intToChar) }
                .joinToString(separator = "")
        ),
        rows = board.size,
        columns = board.first().size
    )

    override fun get(row: Int, column: Int): Int {
        return charToInt(board[row * columns + column])
    }

    override fun canMove(direction: Direction): Boolean {
        return when (direction) {
            Direction.Up -> emptySlotIndex >= columns
            Direction.Down -> emptySlotIndex < rows * columns - columns
            Direction.Left -> emptySlotIndex % columns > 0
            Direction.Right -> emptySlotIndex / (columns - 1) == 0
        }
    }

    override fun move(direction: Direction) {
        require(canMove(direction)) { "Move \"$direction\" is not possible for board $this" }
        val destination = when (direction) {
            Direction.Up -> emptySlotIndex - columns
            Direction.Down -> emptySlotIndex + columns
            Direction.Left -> emptySlotIndex - 1
            Direction.Right -> emptySlotIndex + 1
        }
        board[emptySlotIndex] = board[destination]
        board[destination] = '0'
        emptySlotIndex = destination
    }

    override fun isSolved(): Boolean {
        return board.toString() == solvedValue(rows, columns)
    }

    override fun copy(): Board {
        return StringBoard(
            board = StringBuilder(board.toString()),
            emptySlotIndex = emptySlotIndex,
            rows = rows,
            columns = columns
        )
    }

    override fun hashCode(): Int {
        return 31 + 47 * rows + 63 * columns + board.toString().hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val toCompare = other as? StringBoard ?: return false
        return board.toString() == toCompare.board.toString() &&
            rows == toCompare.rows &&
            columns == toCompare.columns
    }

    override fun toString(): String {
        val str = mutableListOf<String>()
        for (row in 0 until rows) {
            board
                .substring(row * columns until row * columns + columns)
                .map(::charToInt)
                .joinToString(prefix = "[", separator = ",", postfix = "]")
                .let(str::add)
        }
        return str.joinToString(prefix = "StringBoard[", separator = ",", postfix = "]")
    }

    internal companion object {
        private const val SINGLE_DIGIT_CODE_DIFF = '0'.code
        private const val TWO_DIGITS_CODE_DIFF = 'A'.code
        private const val SMALLEST_TWO_DIGITS_NUMBER = 10
        private val solvedValues = mutableMapOf<Int, String>()

        fun solvedValue(rows: Int, columns: Int): String {
            solvedValues[rows * columns]?.let { return it }
            return (1 until rows * columns)
                .map(::intToChar)
                .fold(StringBuilder(), StringBuilder::append)
                .append("0")
                .toString()
                .also { solvedValues[rows * columns] = it }
        }

        fun intToChar(value: Int): Char = when {
            value < SMALLEST_TWO_DIGITS_NUMBER -> value + SINGLE_DIGIT_CODE_DIFF
            else -> value + TWO_DIGITS_CODE_DIFF
        }.let(::Char)

        fun charToInt(c: Char): Int = when {
            c.code >= TWO_DIGITS_CODE_DIFF -> c.code - TWO_DIGITS_CODE_DIFF
            else -> c.code - SINGLE_DIGIT_CODE_DIFF
        }
    }
}
