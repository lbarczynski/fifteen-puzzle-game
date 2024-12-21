package dev.bapps.fifteenpuzzle.game.engine.board

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StringBoardFactoryTest {

    private lateinit var stringBoardFactory: BoardFactory

    @BeforeTest
    fun setup() {
        stringBoardFactory = StringBoardFactory()
    }

    @Test
    fun `GIVEN board values array WHEN create board THEN should return proper string board`() {
        // given
        val values = arrayOf(
            intArrayOf(8, 0, 7),
            intArrayOf(2, 1, 6),
            intArrayOf(3, 4, 5),
        )

        // when
        val result = stringBoardFactory.createBoard(values)

        // then
        assertEquals(StringBoard(values), result)
    }
}
