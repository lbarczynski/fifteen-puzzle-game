package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board

internal interface BoardPreVerifier {
    fun isSolvable(board: Board): Boolean
}

internal interface BoardSolutionVerifier {
    fun isSolved(board: Board): Boolean
}

internal class BoardVerifier : BoardPreVerifier, BoardSolutionVerifier {

    /*
        Verify if board is solvable
        Implementation base on article: https://www.geeksforgeeks.org/check-instance-15-puzzle-solvable/
     */
    override fun isSolvable(board: Board): Boolean {
        val values: List<Int> = flatten(board)
        val inversions = countInversions(values.filter { it != 0 })
        val emptySlotRow = values.indexOf(0) / board.columns

        return when {
            board.rows % 2 == 1 || emptySlotRow % 2 == 1 -> inversions % 2 == 0
            else -> inversions % 2 == 1
        }
    }

    private fun countInversions(values: List<Int>): Int {
        var inversions = 0
        for (i in values.indices) {
            for (j in i + 1..values.lastIndex) {
                if (values[i] > values[j]) {
                    ++inversions
                }
            }
        }
        return inversions
    }

    private fun flatten(board: Board): List<Int> = buildList {
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                add(board[row, column])
            }
        }
    }

    override fun isSolved(board: Board): Boolean {
        val maxValue = board.rows * board.columns
        var expectedValue = 1
        for (row in 0 until board.rows) {
            for (column in 0 until board.columns) {
                if (board[row, column] != expectedValue % maxValue) {
                    return false
                }
                ++expectedValue
            }
        }

        return true
    }
}
