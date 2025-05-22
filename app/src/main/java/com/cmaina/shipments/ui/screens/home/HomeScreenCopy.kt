// File: com/cmaina/shipments/ui/screens/home/HomeScreen.kt
package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmaina.shipments.ui.screens.home.components.SearchResultsList

// Assuming these composables are in the same package or imported correctly:
// import com.cmaina.shipments.ui.screens.home.HomeHeaderSection
// import com.cmaina.shipments.ui.screens.home.HomeSearchBar
// import com.cmaina.shipments.ui.screens.home.TrackingSection
// import com.cmaina.shipments.ui.screens.home.AvailableVehiclesSection

// Colors (ensure these are defined, e.g., in a Colors.kt or at the top of this file if not already)
// val HomeScreenHeaderColor = Color(0xFF4A004E)
// val SearchBarIconBackgroundColor = Color(0xFFFFA726)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCopy(
    homeViewModel: HomeViewModel = viewModel()
    // navController: NavHostController // If needed for internal navigation
) {
    val uiState by homeViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            // ViewModel should have a way to clear the message after it's shown
            // e.g., homeViewModel.clearErrorMessage()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
        // bottomBar = { /* Handled by ShipmentsNavigation in your app's main structure */ }
    ) { innerPadding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding()), // Apply only top padding from scaffold
                contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding() + 16.dp) // Add to bottom padding
            ) {
                // Section 1: Top Header (User Info & Notifications)
                item {
                    uiState.userDisplayInfo?.let { userInfo ->
                        HomeHeaderSection(
                            userDisplayInfo = userInfo,
                            notificationCount = uiState.notificationCount,
                            onLocationClick = { homeViewModel.onLocationClicked() },
                            onNotificationClick = { homeViewModel.onNotificationClicked() }
                        )
                    }
                }

                // Section 2: Search Bar
                item {
                    HomeSearchBar(
                        searchQuery = uiState.searchQuery,
                        onQueryChange = homeViewModel::onSearchQueryChanged, // Using function reference
                        onScanClick = homeViewModel::onScanReceiptClicked, // Using function reference
                        onFocused = { homeViewModel.setSearchActive(true)}// Using function reference
                    )
                }

                item {
                    Box {
                        // Spacer after search bar
                        Column {
                            Spacer(modifier = Modifier.height(24.dp))
                            // Section 3: Tracking Information
                            // Only show if there's tracking info available
                            uiState.activeTrackingSummary?.let { trackingInfo ->
                                TrackingSection(
                                    trackingInfo = trackingInfo,
                                    onAddStopClick = homeViewModel::onAddStopClicked // Using function reference
                                )
                            }

                            // Spacer after tracking (only if tracking info was present)
                            if (uiState.activeTrackingSummary != null) {
                                Spacer(modifier = Modifier.height(24.dp))
                            }

                            // Section 4: Available Vehicles
                            if (uiState.availableVehicles.isNotEmpty()) {
                                AvailableVehiclesSection(
                                    vehicles = uiState.availableVehicles,
                                    onVehicleSelected = homeViewModel::onVehicleOptionSelected // Using function reference
                                )
                            } else if (uiState.activeTrackingSummary == null) {
                                // Fallback content if no tracking and no vehicles (and not loading)
                                Box(
                                    modifier = Modifier
                                        .fillParentMaxHeight(0.5f) // Take up some space
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "No information to display at the moment.",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                        if (uiState.isSearchActive){
                            SearchResultsList(
                                results = uiState.searchResults,
                                isLoading = uiState.isSearchLoading,
                                searchQuery = uiState.searchQuery,
                                onItemClick = { /* Handle item click */ }
                            )
                        }
                    }
                }
            }
        }
    }
}

// Make sure your preview uses a ViewModel instance that populates the state
@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun HomeScreenCopyIntegratedPreview() {
    MaterialTheme {
//        HomeScreen(homeViewModel = HomeViewModel()) // ViewModel init will load sample data
    }
}