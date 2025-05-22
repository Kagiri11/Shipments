// File: com/cmaina/shipments/ui/screens/home/HomeSearchBar.kt
// (Can be in HomeScreen.kt or a new HomeComponents.kt)
package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner // Example for the scan icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.ui.theme.ShipmentsPurple

// Color defined in HomeScreen.kt or your Theme.kt
// val SearchBarIconBackgroundColor = Color(0xFFFFA726) // Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onScanClick: () -> Unit,
    onFocused: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ShipmentsPurple)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onFocused()
                    }
                },
            placeholder = { Text("Enter the receipt number ...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = onScanClick) {
                    Box(
                        modifier = Modifier
                            .size(36.dp) // Size of the orange circle
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.QrCodeScanner, // Or your specific icon
                            contentDescription = "Scan Receipt",
                            tint = Color.White, // Icon color on orange background
                            modifier = Modifier.size(20.dp) // Size of the icon itself
                        )
                    }
                }
            },
            shape = RoundedCornerShape(28.dp), // Highly rounded corners
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface, // White or light surface
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent, // No underline indicator when focused
                unfocusedIndicatorColor = Color.Transparent, // No underline indicator when unfocused
                disabledIndicatorColor = Color.Transparent,
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0) // Light grey background for contrast
@Composable
fun HomeSearchBarPreview() {
    var query by remember { mutableStateOf("") }
    MaterialTheme {
        HomeSearchBar(
            searchQuery = query,
            onQueryChange = { query = it },
            onScanClick = {},
            onFocused = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun HomeSearchBarWithTextPreview() {
    var query by remember { mutableStateOf("NEJ12345") }
    MaterialTheme {
        HomeSearchBar(
            searchQuery = query,
            onQueryChange = { query = it },
            onScanClick = {},
            onFocused = {}
        )
    }
}