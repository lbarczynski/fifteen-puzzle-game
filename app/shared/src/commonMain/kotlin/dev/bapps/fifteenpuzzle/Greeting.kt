package dev.bapps.fifteenpuzzle

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello World, Platform: ${platform.name}!"
    }
}