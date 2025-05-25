package com.cmaina.shipments.ui.screens.calculate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.NorthEast // For Sender
import androidx.compose.material.icons.outlined.Scale // For Weight
import androidx.compose.material.icons.outlined.SouthEast // For Receiver
import androidx.compose.material.icons.outlined.Unarchive
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.ui.screens.calculate.components.CalculateFormField
import com.cmaina.shipments.ui.screens.calculate.components.CalculateScreenUiState
import com.cmaina.shipments.ui.screens.calculate.components.FormEntryField // Import FormEntryField

@Composable
fun DestinationSection(
    uiState: CalculateScreenUiState,
    onSenderLocationClick: () -> Unit,
    onReceiverLocationClick: () -> Unit,
    onApproxWeightClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Destination",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FormEntryField(
                    icon = Icons.Outlined.Unarchive,
                    label = uiState.senderLocation.ifBlank { "Sender location" },
                    onClick = onSenderLocationClick,
                    errorMessage = uiState.fieldErrors[CalculateFormField.SENDER_LOCATION]
                )
                FormEntryField(
                    icon = Icons.Outlined.Archive,
                    label = uiState.receiverLocation.ifBlank { "Receiver location" },
                    onClick = onReceiverLocationClick,
                    errorMessage = uiState.fieldErrors[CalculateFormField.RECEIVER_LOCATION]
                )
                FormEntryField(
                    icon = Icons.Outlined.Scale,
                    label = uiState.approxWeight.ifBlank { "Approx weight" },
                    onClick = onApproxWeightClick,
                    errorMessage = uiState.fieldErrors[CalculateFormField.APPROX_WEIGHT]
                )
            }
        }
    }
}
