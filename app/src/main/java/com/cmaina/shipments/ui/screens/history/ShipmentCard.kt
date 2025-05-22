package com.cmaina.shipments.ui.screens.history

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert // Placeholder for the box icon
import androidx.compose.material.icons.outlined.Autorenew // For in-progress
import androidx.compose.material.icons.outlined.Schedule // For pending
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.shipments.domain.model.Shipment
import com.cmaina.shipments.domain.model.ShipmentStatus
import com.cmaina.shipments.utils.getSampleShipments
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Helper function to format the date (can be moved to a utility file)
fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return formatter.format(date)
}

// Helper data class for status tag display properties
private data class StatusDisplay(
    val text: String,
    val icon: ImageVector,
    val backgroundColor: Color,
    val contentColor: Color
)

@Composable
private fun getStatusDisplay(status: ShipmentStatus): StatusDisplay {
    return when (status) {
        ShipmentStatus.IN_PROGRESS -> StatusDisplay(
            text = "in-progress",
            icon = Icons.Outlined.Autorenew,
            backgroundColor = Color(0xFFE0F7FA), // Light Cyan/Greenish
            contentColor = Color(0xFF00796B)    // Dark Cyan/Greenish
        )
        ShipmentStatus.PENDING -> StatusDisplay(
            text = "Pending",
            icon = Icons.Outlined.Schedule,
            backgroundColor = Color(0xFFFFF9C4), // Light Yellow/Orangey
            contentColor = Color(0xFFFBC02D)    // Dark Yellow/Orangey
        )
        ShipmentStatus.COMPLETED -> StatusDisplay( // Added for completeness
            text = "Completed",
            icon = Icons.Outlined.Schedule, // Placeholder, choose appropriate icon
            backgroundColor = Color(0xFFE8E8E8), // Light Grey
            contentColor = Color(0xFF5E5E5E)     // Dark Grey
        )

        ShipmentStatus.CANCELLED -> TODO()
        ShipmentStatus.LOADING -> TODO()
    }
}

@Composable
fun ShipmentStatusTag(status: ShipmentStatus, modifier: Modifier = Modifier) {
    val statusDisplay = getStatusDisplay(status)
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Pill shape
            .background(statusDisplay.backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = statusDisplay.icon,
            contentDescription = statusDisplay.text,
            tint = statusDisplay.contentColor,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = statusDisplay.text,
            color = statusDisplay.contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ShipmentItemCard(
    shipment: Shipment,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 16.dp), // Added horizontal padding to match screen
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Inner padding for the card content
                .fillMaxWidth()
        ) {
            // Top Row: Status Tag
            ShipmentStatusTag(status = shipment.status)

            Spacer(modifier = Modifier.height(12.dp))

            // Main Title
            Text(
                text = shipment.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Middle Row: Description and Image
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top, // Or CenterVertically if preferred
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = shipment.fullDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f) // Takes available space
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Placeholder Box Icon
                Icon(
                    imageVector = Icons.Filled.MoreVert, // Replace with your actual box icon
                    contentDescription = "Shipment Package",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier
                        .size(48.dp) // Adjust size as needed
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Row: Price and Date
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "${shipment.price} ${shipment.currency}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "•",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = formatDate(shipment.estimatedArrivalDate),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentItemCardInProgressPreview() {
    val sampleShipment = getSampleShipments().first { it.status == ShipmentStatus.IN_PROGRESS }
    MaterialTheme { // Wrap with MaterialTheme for preview
        ShipmentItemCard(shipment = sampleShipment)
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentItemCardPendingPreview() {
    val sampleShipment = getSampleShipments().first { it.status == ShipmentStatus.PENDING }
    MaterialTheme { // Wrap with MaterialTheme for preview
        ShipmentItemCard(shipment = sampleShipment)
    }
}