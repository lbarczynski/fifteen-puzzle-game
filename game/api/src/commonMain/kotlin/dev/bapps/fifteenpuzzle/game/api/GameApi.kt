package dev.bapps.fifteenpuzzle.game.api

class GameApi {
    fun helloWorld(): String = HELLO_WORLD_MESSAGE

    private companion object {
        const val HELLO_WORLD_MESSAGE = "Hello Game API!"
    }
}
