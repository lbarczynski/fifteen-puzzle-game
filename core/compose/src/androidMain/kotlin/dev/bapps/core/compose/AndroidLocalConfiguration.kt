package dev.bapps.core.compose

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal class AndroidLocalConfiguration(
    private val platformLocalConfigurationProvider: ProvidableCompositionLocal<Configuration>
) : LocalConfiguration {

    override val screen: Screen
        @Composable get() = loadScreenProperties()

    @Composable
    private fun loadScreenProperties(): Screen {
        return with(platformLocalConfigurationProvider.current) {
            object : Screen {
                override val width: Dp = screenWidthDp.dp
                override val height: Dp = screenHeightDp.dp
            }
        }
    }
}

actual fun localConfiguration(): LocalConfiguration {
    return AndroidLocalConfiguration(
        platformLocalConfigurationProvider = androidx.compose.ui.platform.LocalConfiguration
    )
}
