package com.cmaina.shipments.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.tracking.TrackingParty
import com.cmaina.shipments.domain.model.user.UserDisplayInfo
import com.cmaina.shipments.domain.model.vehicles.VehicleOption
import kotlinx.coroutines.delay // For simulating network/data operations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeScreenData()
    }

    private fun loadHomeScreenData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            // Simulate fetching various pieces of data.
            // In a real app, these would be calls to UseCases or Repositories.
            delay(1500) // Simulate overall loading time

            val fetchedUserDisplayInfo = provideSampleUserDisplayInfo()
            val fetchedTrackingSummary = provideSampleActiveTrackingSummary()
            val fetchedVehicles = provideSampleVehicleOptions()
            val fetchedNotificationCount = 3 // Example

            _uiState.update {
                it.copy(
                    isLoading = false,
                    userDisplayInfo = fetchedUserDisplayInfo,
                    activeTrackingSummary = fetchedTrackingSummary,
                    availableVehicles = fetchedVehicles,
                    notificationCount = fetchedNotificationCount,
                    errorMessage = null // Clear previous errors on successful load
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        // Here you could add logic to perform a search if the query was for an active search
        // For "Enter the receipt number...", this might trigger a lookup.
    }

    fun onScanReceiptClicked() {
        // TODO: Implement logic for scanning a receipt (e.g., open camera)
        _uiState.update { it.copy(errorMessage = "Scan feature not implemented.") }
    }

    fun onNotificationClicked() {
        // TODO: Implement navigation to notifications screen or show a dialog
        _uiState.update { it.copy(errorMessage = "Notifications not implemented.") }
    }

    fun onLocationClicked() {
        // TODO: Implement logic to change location
        _uiState.update { it.copy(errorMessage = "Location change not implemented.") }
    }

    fun onAddStopClicked() {
        // TODO: Implement logic for "Add Stop"
        _uiState.update { it.copy(errorMessage = "Add Stop not implemented.") }
    }

    fun onVehicleOptionSelected(vehicleId: String) {
        // TODO: Implement logic for when a vehicle option is selected
        _uiState.update { it.copy(errorMessage = "Vehicle selection for '$vehicleId' not implemented.") }
    }


    // --- Sample Data Providers (Replace with actual data sources) ---
    private fun provideSampleUserDisplayInfo(): UserDisplayInfo {
        return UserDisplayInfo(
            // You can use a real image URL for testing if you have one, or keep it null
            profileImageUrl = "https://picsum.photos/seed/user1/100/100",
            currentLocation = "Wertheimer, Illinois"
        )
    }

    private fun provideSampleActiveTrackingSummary(): ActiveTrackingSummary {
        return ActiveTrackingSummary(
            shipmentNumber = "NEJ20089934122231",
            vehicleIconUrl = null, // Or a placeholder/test image URL for the forklift
            sender = TrackingParty(city = "Atlanta", locationCode = "5243"),
            receiver = TrackingParty(city = "Chicago", locationCode = "6342"),
            deliveryTimeEstimate = "2 day -3 days",
            statusDescription = "Waiting to collect",
            allowAddStopFeature = true
        )
    }

    private fun provideSampleVehicleOptions(): List<VehicleOption> {
        return listOf(
            VehicleOption(
                id = "ocean_freight_01",
                name = "Ocean freight",
                description = "International",
                // Replace with actual or placeholder image URLs
                imageUrl = "https://picsum.photos/seed/ocean/300/200"
            ),
            VehicleOption(
                id = "cargo_freight_02",
                name = "Cargo freight",
                description = "Reliable",
                imageUrl = "https://picsum.photos/seed/cargo/300/200"
            ),
            VehicleOption(
                id = "air_freight_03",
                name = "Air freight",
                description = "International",
                imageUrl = "https://picsum.photos/seed/air/300/200"
            )
        )
    }
}