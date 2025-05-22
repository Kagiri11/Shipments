package com.cmaina.shipments.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.shipments.domain.model.Shipment
import com.cmaina.shipments.domain.model.ShipmentFilterType
import com.cmaina.shipments.domain.model.ShipmentStatus
import com.cmaina.shipments.ui.model.TabItem
import com.cmaina.shipments.utils.getSampleShipments
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShipmentHistoryViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ShipmentHistoryUiState())
    val uiState: StateFlow<ShipmentHistoryUiState> = _uiState.asStateFlow()

    private var allShipments: List<Shipment> = emptyList()

    init {
        loadInitialShipments()
    }

    private fun loadInitialShipments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // Simulate network delay or data fetching
            delay(1000) // Remove this in a real app
            allShipments = getSampleShipments() // Replace with actual data source call

            val calculatedTabItems = calculateTabItems(allShipments)
            val initialSelectedTabIndex = 0 // Default to "All"
            val initialDisplayedShipments = filterShipments(
                filterType = calculatedTabItems[initialSelectedTabIndex].type,
                sourceShipments = allShipments
            )

            _uiState.update {
                it.copy(
                    isLoading = false,
                    tabItems = calculatedTabItems,
                    selectedTabIndex = initialSelectedTabIndex,
                    displayedShipments = initialDisplayedShipments
                )
            }
        }
    }

    fun onTabSelected(newIndex: Int) {
        if (newIndex < 0 || newIndex >= _uiState.value.tabItems.size) return

        val selectedFilterType = _uiState.value.tabItems[newIndex].type
        val filtered = filterShipments(selectedFilterType, allShipments)

        _uiState.update {
            it.copy(
                selectedTabIndex = newIndex,
                displayedShipments = filtered
            )
        }
    }

    private fun calculateTabItems(sourceShipments: List<Shipment>): List<TabItem> {
        return ShipmentFilterType.values().map { filterType ->
            val count = when (filterType) {
                ShipmentFilterType.ALL -> sourceShipments.size
                ShipmentFilterType.COMPLETED -> sourceShipments.count { it.status == ShipmentStatus.COMPLETED }
                ShipmentFilterType.IN_PROGRESS -> sourceShipments.count { it.status == ShipmentStatus.IN_PROGRESS }
                ShipmentFilterType.PENDING -> sourceShipments.count { it.status == ShipmentStatus.PENDING }
                // Add cases for other statuses if your ShipmentFilterType enum grows
            }
            TabItem(type = filterType, count = count)
        }
    }

    private fun filterShipments(
        filterType: ShipmentFilterType,
        sourceShipments: List<Shipment>
    ): List<Shipment> {
        return when (filterType) {
            ShipmentFilterType.ALL -> sourceShipments
            ShipmentFilterType.COMPLETED -> sourceShipments.filter { it.status == ShipmentStatus.COMPLETED }
            ShipmentFilterType.IN_PROGRESS -> sourceShipments.filter { it.status == ShipmentStatus.IN_PROGRESS }
            ShipmentFilterType.PENDING -> sourceShipments.filter { it.status == ShipmentStatus.PENDING }
            // Add cases for other statuses
        }
    }
}

data class ShipmentHistoryUiState(
    val displayedShipments: List<Shipment> = emptyList(), // Renamed for clarity
    val tabItems: List<TabItem> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading: Boolean = false,
    val errorMessage: String = "" // We'll ignore this for now as requested
)