package dev.bapps.fifteenpuzzle.game.api

interface Board {
    val rows: Int
    val columns: Int

    operator fun get(row: Int, column: Int): Int

    fun canMove(direction: Direction): Boolean
    fun move(direction: Direction)
    fun copy(): Board
}

fun Board.canMoveUp(): Boolean = canMove(Direction.Up)
fun Board.canMoveDown(): Boolean = canMove(Direction.Down)
fun Board.canMoveLeft(): Boolean = canMove(Direction.Left)
fun Board.canMoveRight(): Boolean = canMove(Direction.Right)

fun Board.moveUp() = move(Direction.Up)
fun Board.moveDown() = move(Direction.Down)
fun Board.moveLeft() = move(Direction.Left)
fun Board.moveRight() = move(Direction.Right)
