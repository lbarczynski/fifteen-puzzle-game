package dev.bapps.fifteenpuzzle.game.api

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import dev.mokkery.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

internal class BoardTest {

    private lateinit var board: Board

    @BeforeTest
    fun setup() {
        board = mock<Board>()
    }

    @Test
    fun `GIVEN board WHEN move up extension function THEN should execute move with up direction`() {
        // given
        every { board.move(Direction.Up) } returns Unit

        // when
        board.moveUp()

        // then
        verify { board.move(Direction.Up) }
    }

    @Test
    fun `GIVEN board WHEN move down extension function THEN should execute move with down direction`() {
        // given
        every { board.move(Direction.Down) } returns Unit

        // when
        board.moveDown()

        // then
        verify { board.move(Direction.Down) }
    }

    @Test
    fun `GIVEN board WHEN move left extension function THEN should execute move with left direction`() {
        // given
        every { board.move(Direction.Left) } returns Unit

        // when
        board.moveLeft()

        // then
        verify { board.move(Direction.Left) }
    }

    @Test
    fun `GIVEN board WHEN move right extension function THEN should execute move with right direction`() {
        // given
        every { board.move(Direction.Right) } returns Unit

        // when
        board.moveRight()

        // then
        verify { board.move(Direction.Right) }
    }

    @Test
    fun `GIVEN board WHEN can move up extension function THEN should execute can move with up direction`() {
        // given
        every { board.canMove(Direction.Up) } returns true

        // when
        val result = board.canMoveUp()

        // then
        assertTrue(result)
        verify { board.canMove(Direction.Up) }
    }

    @Test
    fun `GIVEN board WHEN can move down extension function THEN should execute can move with down direction`() {
        // given
        every { board.canMove(Direction.Down) } returns true

        // when
        val result = board.canMoveDown()

        // then
        assertTrue(result)
        verify { board.canMove(Direction.Down) }
    }

    @Test
    fun `GIVEN board WHEN can move left extension function THEN should execute can move with left direction`() {
        // given
        every { board.canMove(Direction.Left) } returns true

        // when
        val result = board.canMoveLeft()

        // then
        assertTrue(result)
        verify { board.canMove(Direction.Left) }
    }

    @Test
    fun `GIVEN board WHEN can move right extension function THEN should execute can move with right direction`() {
        // given
        every { board.canMove(Direction.Right) } returns true

        // when
        val result = board.canMoveRight()

        // then
        assertTrue(result)
        verify { board.canMove(Direction.Right) }
    }
}
