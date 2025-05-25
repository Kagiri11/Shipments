package com.cmaina.shipments.ui.screens.history

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmaina.shipments.domain.model.Shipment
import com.cmaina.shipments.ui.theme.ShipmentsPurple
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentHistoryScreen(
    viewModel: ShipmentHistoryViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ShipmentHistoryTopAppBar(onNavigationIconClick = onNavigateBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            if (uiState.tabItems.isNotEmpty()) {
                ShipmentFilterTabs(
                    tabs = uiState.tabItems,
                    selectedTabIndex = uiState.selectedTabIndex,
                    onTabSelected = { index ->
                        viewModel.onTabSelected(index)
                    }
                )
            }

            AnimatedContent(
                targetState = uiState.isLoading
            ) { isLoading ->
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = ShipmentsSmokeWhite),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    ShipmentHistoryContent(
                        shipments = uiState.displayedShipments
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShipmentHistoryContent(
    shipments: List<Shipment>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ShipmentsSmokeWhite)
    ) {
        Text(
            text = "Shipments",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        )

        if (shipments.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No shipments to display.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                items(items = shipments) { shipment ->
                    ShipmentItemCard(
                        modifier = Modifier.animateItem(
                            fadeInSpec = spring(),
                            fadeOutSpec = spring()
                        ),
                        shipment = shipment
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentHistoryScreenPreview() {
    MaterialTheme {
        ShipmentHistoryScreen {}
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentHistoryScreenEmptyPreview() {
    MaterialTheme {
        ShipmentHistoryContent(shipments = emptyList())
    }
}