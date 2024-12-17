package dev.bapps.fifteenpuzzle.game.engine

import dev.bapps.fifteenpuzzle.game.api.Board
import dev.bapps.fifteenpuzzle.game.api.Game
import dev.bapps.fifteenpuzzle.game.engine.board.BoardGenerator
import dev.bapps.fifteenpuzzle.game.engine.board.BoardPreVerifier
import dev.mokkery.answering.returns
import dev.mokkery.answering.sequentiallyReturns
import dev.mokkery.every
import dev.mokkery.mock
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FifteenPuzzleGameGeneratorTest {

    private lateinit var boardGenerator: BoardGenerator
    private lateinit var boardPreVerifier: BoardPreVerifier
    private lateinit var generator: Game.Generator

    @BeforeTest
    fun setup() {
        boardGenerator = mock()
        boardPreVerifier = mock()
        generator = FifteenPuzzleGame.Generator(
            boardGenerator = boardGenerator,
            boardPreVerifier = boardPreVerifier
        )
    }

    @Test
    fun `GIVEN rows and columns WHEN create new game THEN should return first solvable board`() {
        // given
        val rows = 6
        val columns = 9
        val firstBoard: Board = mock()
        val secondBoard: Board = mock()
        val thirdBoard: Board = mock()
        every { boardGenerator.random(rows, columns) } sequentiallyReturns listOf(firstBoard, secondBoard, thirdBoard)
        every { boardPreVerifier.isSolvable(firstBoard) } returns false
        every { boardPreVerifier.isSolvable(secondBoard) } returns false
        every { boardPreVerifier.isSolvable(thirdBoard) } returns true

        // when
        val game: Game = generator.createNewGame(rows, columns)

        // then
        assertEquals(thirdBoard, game.state.value)
    }
}
