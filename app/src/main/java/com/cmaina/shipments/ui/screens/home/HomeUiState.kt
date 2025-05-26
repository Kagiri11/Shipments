package com.cmaina.shipments.ui.screens.home

import com.cmaina.shipments.domain.model.search.ShipmentSearchResult
import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.user.UserDisplayInfo
import com.cmaina.shipments.domain.model.vehicles.VehicleOption

data class HomeUiState(
    val userDisplayInfo: UserDisplayInfo? = null,
    val notificationCount: Int = 0,
    val searchQuery: String = "",
    val activeTrackingSummary: ActiveTrackingSummary? = null,
    val availableVehicles: List<VehicleOption> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,

    val isSearchActive: Boolean = false,
    val searchResults: List<ShipmentSearchResult> = emptyList(),
    val isSearchLoading: Boolean = false
)