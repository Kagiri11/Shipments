package com.cmaina.shipments.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.shipments.domain.model.search.ShipmentSearchResult
import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.tracking.TrackingParty
import com.cmaina.shipments.domain.model.user.UserDisplayInfo
import com.cmaina.shipments.domain.model.vehicles.VehicleOption
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay // For simulating network/data operations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

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

    // Called when the user taps the search bar or it gains focus
    fun setSearchActive(isActive: Boolean) {
        _uiState.update { it.copy(isSearchActive = isActive) }
        if (!isActive) {
            // If search is deactivated, clear query and results
            _uiState.update { it.copy(searchQuery = "", searchResults = emptyList(), isSearchLoading = false) }
            searchJob?.cancel()
        } else if (_uiState.value.searchQuery.isNotBlank()) {
            // If activating search and there's already a query, trigger search
            onSearchQueryChanged(_uiState.value.searchQuery)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        if (query.isBlank() && _uiState.value.isSearchActive) {
            // If query is blank but search was active, maybe just clear results but stay in search mode
            // Or, call onSearchCancelled() if blank query means exiting search mode.
            // For now, let's assume blank query just clears results if search is active.
            _uiState.update { it.copy(searchResults = emptyList(), isSearchLoading = false) }
            searchJob?.cancel()
            return
        }

        if (_uiState.value.isSearchActive) { // Only search if search mode is active
            _uiState.update { it.copy(isSearchLoading = true) }
            searchJob?.cancel() // Cancel previous search job
            searchJob = viewModelScope.launch {
                delay(500) // Debounce: simulate network delay for search
                val results = performSearch(query)
                _uiState.update {
                    it.copy(
                        searchResults = results,
                        isSearchLoading = false
                    )
                }
            }
        }
    }

    // Called when the user taps the back arrow displayed during search mode
    fun onBackFromSearchClicked() {
        setSearchActive(false)
    }

    // --- Mock search implementation ---
    private fun performSearch(query: String): List<ShipmentSearchResult> {
        if (query.isBlank()) return emptyList()
        // Simulate filtering based on a predefined list
        val allPossibleResults = listOf(
            ShipmentSearchResult("1", "Macbook pro M2", "#NE43857340857904", "Paris", "Morocco"),
            ShipmentSearchResult("2", "Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris"),
            ShipmentSearchResult("3", "Tapered-fit jeans AW", "#NEJ35870264978659", "Colombia", "Paris"),
            ShipmentSearchResult("4", "Slim fit jeans AW", "#NEJ20089934122239", "Bogota", "Dhaka"),
            ShipmentSearchResult("5", "Office setup desk", "#NEJ23481570754963", "France", "German"),
            ShipmentSearchResult("6", "Another Macbook", "#NE111111111111", "USA", "UK")
        )
        return allPossibleResults.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.shipmentNumber.contains(query, ignoreCase = true)
        }
    }

    // ... (keep other ViewModel functions: onScanReceiptClicked, onNotificationClicked, etc.)
    fun onScanReceiptClicked() {
        _uiState.update { it.copy(errorMessage = "Scan feature not implemented.") }
    }

    fun onNotificationClicked() {
        _uiState.update { it.copy(errorMessage = "Notifications not implemented.") }
    }

    fun onLocationClicked() {
        // TODO: Implement logic to change location
        _uiState.update { it.copy(errorMessage = "Location change not implemented.") }
    }

    fun onAddStopClicked() {
        _uiState.update { it.copy(errorMessage = "Add Stop not implemented.") }
    }

    fun onVehicleOptionSelected(vehicleId: String) {
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
            deliveryTimeEstimate = "2 day - 3 days",
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