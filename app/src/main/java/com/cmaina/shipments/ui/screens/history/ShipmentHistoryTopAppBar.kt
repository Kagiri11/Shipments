package com.cmaina.shipments.ui.screens.history

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Auto-mirrored for RTL support
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.ui.theme.ShipmentsPurple

val PurpleAppBarContent = Color.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentHistoryTopAppBar(
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Shipment history",
                style = MaterialTheme.typography.titleLarge,
                color = PurpleAppBarContent
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = PurpleAppBarContent
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = ShipmentsPurple,
            titleContentColor = PurpleAppBarContent,
            navigationIconContentColor = PurpleAppBarContent
        ),
        windowInsets = WindowInsets(top = 0.dp)
    )
}

@Preview
@Composable
fun ShipmentHistoryTopAppBarPreview() {
    MaterialTheme {
        ShipmentHistoryTopAppBar(onNavigationIconClick = {})
    }
}