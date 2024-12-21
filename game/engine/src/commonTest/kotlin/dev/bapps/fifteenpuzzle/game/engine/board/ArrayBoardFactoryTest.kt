package dev.bapps.fifteenpuzzle.game.engine.board

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ArrayBoardFactoryTest {

    private lateinit var arrayBoardFactory: BoardFactory

    @BeforeTest
    fun setup() {
        arrayBoardFactory = ArrayBoardFactory()
    }

    @Test
    fun `GIVEN board values array WHEN create board THEN should return proper array board`() {
        // given
        val values = arrayOf(
            intArrayOf(8, 0, 7),
            intArrayOf(2, 1, 6),
            intArrayOf(3, 4, 5),
        )

        // when
        val result = arrayBoardFactory.createBoard(values)

        // then
        assertEquals(ArrayBoard(values), result)
    }
}
