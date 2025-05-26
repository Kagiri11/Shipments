package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cmaina.shipments.R // For placeholder drawable
import com.cmaina.shipments.domain.model.vehicles.VehicleOption
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

@Composable
fun AvailableVehiclesSection(
    vehicles: List<VehicleOption>,
    onVehicleSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.available_vehicles),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(vehicles, key = { it.id }) { vehicle ->
                VehicleOptionCard(
                    vehicle = vehicle,
                    onClick = { onVehicleSelected(vehicle.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleOptionCard(
    vehicle: VehicleOption,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(160.dp)
            .height(200.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = ShipmentsSmokeWhite)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(vehicle.imageUrl)
                        .apply {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_background)
                            error(R.drawable.ic_launcher_foreground)
                        }.build()
                ),
                contentDescription = vehicle.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .background(ShipmentsSmokeWhite)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = vehicle.name,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = vehicle.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AvailableVehiclesSectionPreview() {
    MaterialTheme {
        AvailableVehiclesSection(
            vehicles = listOf(
                VehicleOption("1", "Ocean freight", "International", "https://picsum.photos/seed/ocean/300/200"),
                VehicleOption("2", "Cargo freight", "Reliable", "https://picsum.photos/seed/cargo/300/200"),
                VehicleOption("3", "Air freight", "International", "https://picsum.photos/seed/air/300/200")
            ),
            onVehicleSelected = {}
        )
    }
}

@Preview
@Composable
fun VehicleOptionCardPreview() {
    MaterialTheme {
        VehicleOptionCard(
            vehicle = VehicleOption(
                id = "prev1",
                name = "Ocean Freight Express",
                description = "Fastest international shipping by sea.",
                imageUrl = "" // Test placeholder display
            ),
            onClick = {}
        )
    }
}