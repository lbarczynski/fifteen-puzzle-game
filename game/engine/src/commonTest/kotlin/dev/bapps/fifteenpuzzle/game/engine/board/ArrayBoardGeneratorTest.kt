package dev.bapps.fifteenpuzzle.game.engine.board

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.mokkery.answering.calls
import dev.mokkery.every
import dev.mokkery.matcher.capture.Capture
import dev.mokkery.matcher.capture.capture
import dev.mokkery.matcher.capture.get
import dev.mokkery.mock
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

internal class ArrayBoardGeneratorTest {

    private lateinit var random: Random
    private lateinit var boardGenerator: BoardGenerator

    @BeforeTest
    fun setup() {
        random = mock()
        boardGenerator = ArrayBoardGenerator(random)
    }

    @Test
    fun `GIVEN rows and columns WHEN random THEN should return randomized array board`() {
        // given
        val rows = 4
        val columns = 4
        val randomUntilCaptureSlot = Capture.slot<Int>()
        every { random.nextInt(until = capture(randomUntilCaptureSlot)) } calls {
            // mocking random to return reverted board values
            if (randomUntilCaptureSlot.get() > rows * columns / 2) {
                rows * columns - randomUntilCaptureSlot.get()
            } else {
                randomUntilCaptureSlot.get() - 1
            }
        }

        // when
        val board = boardGenerator.random(rows, columns)

        // then
        val expectedResult = ((rows * columns - 1) downTo 0).toList()
        assertContentEquals(expectedResult, board.toList())
    }

    private fun Board.toList(): List<Int> = buildList {
        for (row in 0 until rows) {
            for (column in 0 until columns) {
                add(get(row, column))
            }
        }
    }
}
