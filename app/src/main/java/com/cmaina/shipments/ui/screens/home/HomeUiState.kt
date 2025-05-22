package com.cmaina.shipments.ui.screens.home

import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.user.UserDisplayInfo
import com.cmaina.shipments.domain.model.vehicles.VehicleOption

data class HomeUiState(
    val userDisplayInfo: UserDisplayInfo? = null,
    val notificationCount: Int = 0, // For the notification bell icon
    val searchQuery: String = "",   // Current text in the search bar
    val activeTrackingSummary: ActiveTrackingSummary? = null,
    val availableVehicles: List<VehicleOption> = emptyList(),
    val isLoading: Boolean = false, // To show a loading indicator for the whole screen
    val errorMessage: String? = null // To display any errors
)