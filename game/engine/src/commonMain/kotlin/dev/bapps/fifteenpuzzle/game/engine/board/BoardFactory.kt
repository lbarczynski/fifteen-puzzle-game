package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board

internal interface BoardFactory {
    fun createBoard(boardValues: Array<IntArray>): Board
}

internal class ArrayBoardFactory : BoardFactory {
    override fun createBoard(boardValues: Array<IntArray>): Board = ArrayBoard(boardValues)
}

internal class StringBoardFactory : BoardFactory {
    override fun createBoard(boardValues: Array<IntArray>): Board = StringBoard(boardValues)
}
