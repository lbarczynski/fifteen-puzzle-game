package dev.bapps.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import platform.UIKit.UIScreen

internal class AppleLocalConfiguration : LocalConfiguration {

    override val screen: Screen
        @Composable get() = loadScreenProperties()

    @Composable
    @OptIn(ExperimentalComposeUiApi::class)
    private fun loadScreenProperties(): Screen {
        return object : Screen {
            override val width: Dp = LocalWindowInfo.current.containerSize.width.pxToPoint().dp
            override val height: Dp = LocalWindowInfo.current.containerSize.height.pxToPoint().dp
        }
    }

    private fun Int.pxToPoint(): Double = toDouble() / UIScreen.mainScreen.scale
}

actual fun localConfiguration(): LocalConfiguration {
    return AppleLocalConfiguration()
}
