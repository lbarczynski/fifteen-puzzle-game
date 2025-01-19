package dev.bapps.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

interface LocalConfiguration {
    val screen: Screen
        @Composable get
}

interface Screen {
    val width: Dp
    val height: Dp
}

val LocalConfigurationProvider = staticCompositionLocalOf { localConfiguration() }

expect fun localConfiguration(): LocalConfiguration
