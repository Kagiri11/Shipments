// File: com/cmaina/shipments/ui/screens/calculate/components/FormEntryField.kt
package com.cmaina.shipments.ui.screens.calculate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn // Generic location placeholder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val FormFieldBackgroundColor = Color(0xFFF0F0F0) // Light grey

@Composable
fun FormEntryField(
    icon: ImageVector,
    label: String, // This will be the placeholder or the actual displayed value
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(FormFieldBackgroundColor)
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp, vertical = 14.dp), // Adjusted padding for better touch target
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null, // Label serves as description
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = if (label == "Sender location" || label == "Receiver location" || label == "Approx weight" && label == errorMessage) { // Basic check if it's a placeholder
                    MaterialTheme.colorScheme.onSurfaceVariant // Placeholder color
                } else {
                    MaterialTheme.colorScheme.onSurface // Value color
                },
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // Optionally, add a trailing icon like a chevron if all fields open something
            // Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Open", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormEntryFieldPreview() {
    MaterialTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FormEntryField(
                icon = Icons.Outlined.LocationOn,
                label = "Sender location",
                onClick = {}
            )
            FormEntryField(
                icon = Icons.Outlined.LocationOn,
                label = "Nairobi, Kenya", // Example with a value
                onClick = {}
            )
            FormEntryField(
                icon = Icons.Outlined.LocationOn,
                label = "Receiver location",
                onClick = {},
                errorMessage = "This field is required"
            )
        }
    }
}