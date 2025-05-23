// File: com/cmaina/shipments/ui/screens/calculate/CalculateTopAppBar.kt
// Or place directly in CalculateScreen.kt if it's not reused extensively.
package com.cmaina.shipments.ui.screens.calculate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

// Assuming these colors are defined in a central place or accessible
val AppBarBackgroundColor = Color(0xFF4A004E) // Same deep purple
val AppBarContentColor = Color.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateTopAppBar(
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Calculate",
                style = MaterialTheme.typography.titleLarge,
                color = AppBarContentColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = AppBarContentColor
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AppBarBackgroundColor,
            titleContentColor = AppBarContentColor,
            navigationIconContentColor = AppBarContentColor
        )
    )
}

@Preview
@Composable
fun CalculateTopAppBarPreview() {
    MaterialTheme {
        CalculateTopAppBar(onNavigationIconClick = {})
    }
}