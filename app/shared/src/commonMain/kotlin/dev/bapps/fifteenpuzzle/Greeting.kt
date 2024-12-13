package dev.bapps.fifteenpuzzle

import dev.bapps.fifteenpuzzle.game.api.GameApi

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val gameApi = GameApi()
        return "Hello World, Platform: ${platform.name}! + ${gameApi.helloWorld()}"
    }
}
