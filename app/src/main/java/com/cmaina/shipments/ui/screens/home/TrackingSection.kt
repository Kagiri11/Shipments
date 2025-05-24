// File: com/cmaina/shipments/ui/screens/home/TrackingSection.kt
// (Can be in HomeScreen.kt or a new HomeComponents.kt or TrackingComponents.kt)
package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment // Placeholder for Sender/Receiver
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cmaina.shipments.R // For placeholder drawables
import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.tracking.TrackingParty
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

// --- Tracking Section Composable ---
@Composable
fun TrackingSection(
    trackingInfo: ActiveTrackingSummary,
    onAddStopClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Tracking",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        TrackingInfoSection(
            trackingInfo = trackingInfo,
            onAddStopClick = onAddStopClick
        )
    }
}

// --- Tracking Info Composable ---
@Composable
fun TrackingInfoSection(
    trackingInfo: ActiveTrackingSummary,
    onAddStopClick: () -> Unit
) {
    Card(modifier = Modifier.wrapContentHeight(), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Top: Shipment Number and Forklift Icon
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Shipment Number",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = trackingInfo.shipmentNumber,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(trackingInfo.vehicleIconUrl)
                            .apply {
                                crossfade(true)
                                placeholder(R.drawable.ic_launcher_background) // Add placeholder
                                error(R.drawable.ic_launcher_foreground)         // Add error placeholder
                            }.build()
                    ),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(36.dp), // Adjust size as per design
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 2.dp,
                color = ShipmentsSmokeWhite
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Middle: Sender/Time and Receiver/Status Details
            // Row 1: Sender and Time
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TrackingDetailItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFE0B2)) // Light Orange
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Apartment, contentDescription = "Sender Icon", tint = Color(0xFFFB8C00), modifier = Modifier.size(16.dp)) // Darker Orange
                        }
                    },
                    label = "Sender",
                    value = "${trackingInfo.sender.city}, ${trackingInfo.sender.locationCode}",
                    modifier = Modifier.weight(1f)
                )
                TrackingDetailItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50)) // Green dot
                        )
                    },
                    label = "Time",
                    value = "• ${trackingInfo.deliveryTimeEstimate}", // Added bullet here for consistency
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Row 2: Receiver and Status
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TrackingDetailItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFC8E6C9)) // Light Green
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.Apartment, contentDescription = "Receiver Icon", tint = Color(0xFF388E3C), modifier = Modifier.size(16.dp)) // Darker Green
                        }
                    },
                    label = "Receiver",
                    value = "${trackingInfo.receiver.city}, ${trackingInfo.receiver.locationCode}",
                    modifier = Modifier.weight(1f)
                )
                TrackingDetailItem(
                    // No specific icon for status in the design, just text
                    icon = null, // No icon
                    label = "Status",
                    value = trackingInfo.statusDescription,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                thickness = 2.dp,
                color = ShipmentsSmokeWhite
            )

            // Bottom: Add Stop Button
            if (trackingInfo.allowAddStopFeature) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Stop Icon",
                        modifier = Modifier.size(18.dp),
                        tint = ShipmentsBrown
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Add Stop",
                        color = ShipmentsBrown)
                }
            }
        }
    }
}

// --- Helper Composable for Detail Items ---
@Composable
fun TrackingDetailItem(
    icon: (@Composable () -> Unit)?, // Nullable if no icon needed
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top // Align icon to top if text wraps
    ) {
        icon?.let {
            Box(modifier = Modifier.padding(top = 4.dp)) { // Align icon with first line of text
                it()
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackingSectionPreview() {
    MaterialTheme {
        TrackingSection(
            trackingInfo = ActiveTrackingSummary(
                shipmentNumber = "NEJ20089934122231",
                vehicleIconUrl = null,
                sender = TrackingParty(city = "Atlanta", locationCode = "5243"),
                receiver = TrackingParty(city = "Chicago", locationCode = "6342"),
                deliveryTimeEstimate = "2 day -3 days",
                statusDescription = "Waiting to collect",
                allowAddStopFeature = true
            ),
            onAddStopClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrackingInfoCardPreview() {
    MaterialTheme {
        TrackingInfoSection(
            trackingInfo = ActiveTrackingSummary(
                shipmentNumber = "PREVIEW123456789",
                vehicleIconUrl = null, // Test placeholder
                sender = TrackingParty(city = "New York", locationCode = "1001"),
                receiver = TrackingParty(city = "Los Angeles", locationCode = "9001"),
                deliveryTimeEstimate = "3 day - 4 days",
                statusDescription = "In Transit",
                allowAddStopFeature = true
            ),
            onAddStopClick = {}
        )
    }
}