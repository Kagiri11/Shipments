package com.cmaina.shipments.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Home: Screen()

    @Serializable
    data object Profile: Screen()

    @Serializable
    data object Calculate: Screen()

    @Serializable
    data object History: Screen()

}