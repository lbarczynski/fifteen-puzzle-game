package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.mokkery.answering.calls
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.capture.Capture
import dev.mokkery.matcher.capture.capture
import dev.mokkery.matcher.capture.get
import dev.mokkery.mock
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class TypedBoardGeneratorTest {

    private lateinit var random: Random
    private lateinit var boardFactory: BoardFactory
    private lateinit var boardGenerator: BoardGenerator

    @BeforeTest
    fun setup() {
        boardFactory = mock()
        random = mock()
        boardGenerator = TypedBoardGenerator(
            boardFactory = boardFactory,
            random = random
        )
    }

    @Test
    fun `GIVEN rows and columns WHEN random THEN should return randomized array board`() {
        // given
        val rows = 4
        val columns = 4
        val randomUntilCaptureSlot = Capture.slot<Int>()
        val boardValuesCaptureSlot = Capture.slot<Array<IntArray>>()
        val board: Board = mock()
        every { random.nextInt(until = capture(randomUntilCaptureSlot)) } calls {
            // mocking random to return reverted board values
            when {
                randomUntilCaptureSlot.get() > rows * columns / 2 -> rows * columns - randomUntilCaptureSlot.get()
                else -> randomUntilCaptureSlot.get() - 1
            }
        }
        every { boardFactory.createBoard(capture(boardValuesCaptureSlot)) } returns board

        // when
        val result = boardGenerator.random(rows, columns)

        // then
        assertEquals(board, result)
        val expectedValues = arrayOf(
            intArrayOf(15, 14, 13, 12),
            intArrayOf(11, 10, 9, 8),
            intArrayOf(7, 6, 5, 4),
            intArrayOf(3, 2, 1, 0),
        )
        assertEquals(expectedValues.size, boardValuesCaptureSlot.get().size)
        assertContentEquals(expectedValues[0], boardValuesCaptureSlot.get()[0])
        assertContentEquals(expectedValues[1], boardValuesCaptureSlot.get()[1])
        assertContentEquals(expectedValues[2], boardValuesCaptureSlot.get()[2])
        assertContentEquals(expectedValues[3], boardValuesCaptureSlot.get()[3])
    }
}
