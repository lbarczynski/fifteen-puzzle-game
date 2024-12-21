package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import kotlin.random.Random

internal interface BoardGenerator {
    fun random(rows: Int, columns: Int): Board
}

internal class TypedBoardGenerator(
    private val boardFactory: BoardFactory,
    private val random: Random = Random.Default
) : BoardGenerator {

    override fun random(rows: Int, columns: Int): Board {
        require(rows > 0) { "Rows must be greater than zero!" }
        require(columns > 0) { "Columns must be greater than zero!" }

        val values = (0 until rows * columns)
            .toList()
            .shuffled(random)

        val board = Array(rows) { row ->
            IntArray(columns) { column ->
                values[row * columns + column]
            }
        }

        return boardFactory.createBoard(board)
    }
}
