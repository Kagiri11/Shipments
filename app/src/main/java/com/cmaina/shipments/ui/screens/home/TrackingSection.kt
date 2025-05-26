package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.R
import com.cmaina.shipments.domain.model.tracking.ActiveTrackingSummary
import com.cmaina.shipments.domain.model.tracking.TrackingParty
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsGrey
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite
import com.cmaina.shipments.ui.theme.fonts

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
            text = stringResource(R.string.tracking),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = fonts
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        TrackingInfoSection(
            trackingInfo = trackingInfo
        )
    }
}

@Composable
fun TrackingInfoSection(
    trackingInfo: ActiveTrackingSummary,
) {
    Card(
        modifier = Modifier.wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Top: Shipment Number and Forklift Icon
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.shipment_number),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                        color = ShipmentsGrey
                    )
                    Text(
                        text = trackingInfo.shipmentNumber,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                }

                Image(
                    painter = painterResource(R.drawable.car_icon),
                    contentDescription = "Vehicle",
                    modifier = Modifier.size(50.dp),
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

            // Row 1: Sender and Time
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(modifier = Modifier.weight(0.5f)) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFE0B2))
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Apartment,
                            contentDescription = stringResource(R.string.sender_icon),
                            tint = Color(0xFFFB8C00),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Column {
                        Text(
                            text = stringResource(R.string.sender),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = stringResource(R.string.atalanta_5243),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }
                }
                TrackingDetailItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50))
                        )
                    },
                    label = stringResource(R.string.time),
                    value = trackingInfo.deliveryTimeEstimate,
                    modifier = Modifier
                        .weight(0.5f)
                        .background(Color.Red)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row (modifier = Modifier.weight(0.5f)){
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFE0B2)) // Light Orange
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Apartment,
                            contentDescription = "Sender Icon",
                            tint = Color(0xFF388E3C),
                            modifier = Modifier.size(16.dp)
                        ) // Darker Orange
                    }
                    Column {
                        Text(
                            text = "Receiver",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "Chicago, 6342",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }
                }

                TrackingDetailItem(
                    // No specific icon for status in the design, just text
                    icon = null, // No icon
                    label = "Status",
                    value = trackingInfo.statusDescription,
                    modifier = Modifier.weight(0.5f)
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Divider(
            thickness = 2.dp,
            color = ShipmentsSmokeWhite
        )

        // Bottom: Add Stop Button
        if (trackingInfo.allowAddStopFeature) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Stop Icon",
                    modifier = Modifier.size(18.dp),
                    tint = ShipmentsBrown
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Add Stop",
                    color = ShipmentsBrown
                )
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
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row {
            icon?.let {
                Box(modifier = Modifier.padding(top = 4.dp)) { // Align icon with first line of text
                    it()
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
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
            )
        )
    }
}