package dev.bapps.fifteenpuzzle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform