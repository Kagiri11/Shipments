package com.cmaina.shipments.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val ColorScheme = lightColorScheme(
    primary = ShipmentsPurple,
    secondary = Color.White,
    surface = ShipmentsSmokeWhite
)

@Composable
fun ShipmentsTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}