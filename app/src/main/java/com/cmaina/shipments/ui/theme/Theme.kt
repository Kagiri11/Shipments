package com.cmaina.shipments.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val ColorScheme = lightColorScheme(
    primary = ShipmentsPurple,
    secondary = Color.White,
    surface = ShipmentsSmokeWhite
)

@Composable
fun ShipmentsTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = ShipmentsPurple.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
    }
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}