package com.cmaina.shipments.ui.screens.calculate

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmaina.shipments.ui.screens.calculate.components.CalculateScreenUiState

// Import other necessary components as we build them

// Assuming this color is defined (e.g., from Home Screen search bar icon)
val CalculateButtonColor = Color(0xFFFFA726) // Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(
    calculateViewModel: CalculateViewModel = viewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by calculateViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.generalErrorMessage) {
        uiState.generalErrorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            calculateViewModel.clearErrorMessage() // Clear after showing
        }
    }
    // You might also want LaunchedEffect for uiState.calculationResult to show a success message or navigate

    Scaffold(
        topBar = {
            CalculateTopAppBar(onNavigationIconClick = onNavigateBack)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {  innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp) // Horizontal padding for the whole content
                .verticalScroll(rememberScrollState()) // Make content scrollable if it exceeds screen height
        ) {
            // We will add sections here: Destination, Packaging, Categories

            Spacer(modifier = Modifier.height(24.dp)) // Initial spacer

            // Placeholder for Destination Section
            DestinationSection(
                uiState = uiState,
                onSenderLocationClick = { /* viewModel.handleSenderLocationSelection() */ },
                onReceiverLocationClick = { /* viewModel.handleReceiverLocationSelection() */ },
                onApproxWeightClick = { /* viewModel.handleApproxWeightSelection() or focus text field */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Placeholder for Packaging Section
            PackagingSection(
                uiState = uiState,
                onPackagingSelected = {}
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Placeholder for Categories Section
            CategoriesSection(
                uiState = uiState,
                viewModel = calculateViewModel
            )

            Spacer(modifier = Modifier.weight(1f)) // Pushes button to the bottom if content is short

            // Calculate Button
            Button(
                onClick = { calculateViewModel.onCalculateClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                enabled = !uiState.isCalculating,
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = CalculateButtonColor)
            ) {
                if (uiState.isCalculating) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Calculate",
                        color = MaterialTheme.colorScheme.onPrimary
                    ) // Assuming white or dark text on orange
                }
            }

            // Display calculation result (optional, could be a dialog or navigation)
            uiState.calculationResult?.let { result ->
                Text(
                    text = result,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Composable
fun PackagingSection(uiState: CalculateScreenUiState, viewModel: CalculateViewModel) {
    Column {
        Text(
            "Packaging Section Placeholder",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        // ... Implementation will go here ...
        Text("Selected: ${uiState.selectedPackaging}", modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun CategoriesSection(uiState: CalculateScreenUiState, viewModel: CalculateViewModel) {
    Column {
        Text(
            "Categories Section Placeholder",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        // ... Implementation will go here ...
        Text(
            "Selected: ${uiState.selectedCategories.joinToString()}",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun CalculateScreenPreview() {
    MaterialTheme {
        /*CalculateScreen(
            calculateViewModel = CalculateViewModel(), // Uses default state
            onNavigateBack = {}
        )*/
    }
}