package com.cmaina.shipments.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.shipments.domain.model.Shipment
import com.cmaina.shipments.utils.getSampleShipments

// This would typically come from a ViewModel
// For now, we'll use the sample data directly
val sampleShipmentsForScreen = getSampleShipments()

@Composable
fun ShipmentHistoryScreen(
    // viewModel: ShipmentHistoryViewModel = hiltViewModel() // Example with Hilt
) {
    // val shipments by viewModel.shipments.collectAsState() // Example with ViewModel
    val shipments = sampleShipmentsForScreen // Using sample data directly for now

    // The Scaffold will be used later to add the TopAppBar and TabRow
    Scaffold(
        // topBar = { /* We'll add TopAppBar here later */ }
    ) { innerPadding ->
        ShipmentHistoryContent(
            shipments = shipments,
            modifier = Modifier.padding(innerPadding) // Apply padding from Scaffold
        )
    }
}

@Composable
fun ShipmentHistoryContent(
    shipments: List<Shipment>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
        //.padding(horizontal = 16.dp) // Horizontal padding is on cards or screen level
    ) {
        // "Shipments" Header
        Text(
            text = "Shipments",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp) // Adjusted padding
        )

        // List of Shipment Cards
        if (shipments.isEmpty()) {
            // Optional: Show a message if there are no shipments
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text("No shipments to display.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 6.dp) // Padding around the list itself
                // Item padding is handled by the ShipmentItemCard's outer padding now
            ) {
                items(shipments, key = { it.id }) { shipment ->
                    ShipmentItemCard(
                        shipment = shipment
                        // Modifier for the card is already set within ShipmentItemCard
                        // Or you can add specific list item modifiers here if needed
                        // e.g., Modifier.padding(horizontal = 16.dp) if not on card
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun ShipmentHistoryScreenPreview() {
    MaterialTheme { // Ensure your custom theme is applied if you have one
        ShipmentHistoryScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentHistoryScreenEmptyPreview() {
    MaterialTheme {
        ShipmentHistoryContent(shipments = emptyList())
    }
}