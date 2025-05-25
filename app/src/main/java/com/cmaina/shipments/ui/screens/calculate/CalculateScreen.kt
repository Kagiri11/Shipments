package com.cmaina.shipments.ui.screens.calculate

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmaina.shipments.ui.screens.calculate.components.CalculateFormField
import com.cmaina.shipments.ui.screens.calculate.components.CalculateScreenUiState
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateScreen(
    calculateViewModel: CalculateViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToSuccess: () -> Unit
) {
    val uiState by calculateViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.generalErrorMessage) {
        uiState.generalErrorMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            calculateViewModel.clearErrorMessage() // Clear after showing
        }
    }

    LaunchedEffect(uiState.navigateToSuccessScreen) {
        if (uiState.navigateToSuccessScreen){
            onNavigateToSuccess()
            calculateViewModel.resetNavigationUiState()
        }
    }

    Scaffold(
        topBar = {
            CalculateTopAppBar(onNavigationIconClick = onNavigateBack)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {  innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ShipmentsSmokeWhite)
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            DestinationSection(
                uiState = uiState,
                onSenderLocationClick = { },
                onReceiverLocationClick = { },
                onApproxWeightClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Placeholder for Packaging Section
            PackagingSection(
                uiState = uiState,
                onPackagingSelected = { packaging ->
                    calculateViewModel.onPackagingSelected(packaging)
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Placeholder for Categories Section
            CategoriesSection(
                uiState = uiState,
                onCategoryToggled = {
                    calculateViewModel.onCategoryToggled(it)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Calculate Button
            Button(
                onClick = { calculateViewModel.onCalculateClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp).height(60.dp),
                enabled = !uiState.isCalculating,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = ShipmentsBrown)
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
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CategoriesSection(
    uiState: CalculateScreenUiState,
    onCategoryToggled: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "What are you sending?", // Same subtitle, as per design
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.categoryOptions.forEach { categoryName ->
                val isSelected = uiState.selectedCategories.contains(categoryName)
                val selectedContainerColor by animateColorAsState(if (isSelected) Color.Black else ShipmentsSmokeWhite)
                val selectedLabelColor by animateColorAsState(if (isSelected) Color.White else Color.Black)
                FilterChip(
                    selected = isSelected,
                    onClick = { onCategoryToggled(categoryName) },
                    label = { Text(categoryName) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = selectedContainerColor,
                        selectedLabelColor = selectedLabelColor,
                    ),
                )
            }
        }

        uiState.fieldErrors[CalculateFormField.CATEGORIES]?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 8.dp)
            )
        }
    }
}