package com.cmaina.shipments.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val label: String, @Contextual val icon: ImageVector = Icons.Default.Home) {
    @Serializable
    data object Home: Screen(label = "Home", icon = Icons.Rounded.Home)

    @Serializable
    data object Profile: Screen(label = "Profile", icon = Icons.Rounded.AccountCircle)

    @Serializable
    data object Calculate: Screen(label = "Calculate", icon = Icons.Rounded.Calculate)

    @Serializable
    data object History: Screen(label = "Shipment", icon = Icons.Rounded.History)

    @Serializable
    data object Success: Screen(label = "Success")
}