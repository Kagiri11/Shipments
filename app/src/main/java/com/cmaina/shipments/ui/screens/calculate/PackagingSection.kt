package com.cmaina.shipments.ui.screens.calculate

// ... other imports from CalculateScreen.kt ...
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2 // For Packaging Box Icon
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField // Or TextField if you prefer a filled look
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.ui.screens.calculate.components.FormFieldBackgroundColor // Ensure this is accessible

// ... (CalculateTopAppBar, CalculateScreen, DestinationSection, CategoriesSection stub, Previews) ...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagingSection(
    uiState: CalculateScreenUiState,
    onPackagingSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Packaging",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "What are you sending?",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField( // Using OutlinedTextField to match general field appearance
                value = uiState.selectedPackaging,
                onValueChange = {}, // Not directly editable
                readOnly = true,
                modifier = Modifier
                    .menuAnchor() // Important for connecting TextField to the DropdownMenu
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Inventory2,
                        contentDescription = "Packaging Type Icon"
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                shape = MaterialTheme.shapes.small, // RoundedCornerShape(8.dp)
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = FormFieldBackgroundColor,
                    unfocusedContainerColor = FormFieldBackgroundColor,
                    disabledContainerColor = FormFieldBackgroundColor,
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                uiState.packagingOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            onPackagingSelected(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }

        uiState.fieldErrors[CalculateFormField.PACKAGING]?.let { errorMessage ->
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp) // Match FormEntryField error padding
            )
        }
    }
}

// In your main CalculateScreen composable, update the call to PackagingSection:
// ... inside CalculateScreen's Column ...
// PackagingSection(
//     uiState = uiState,
//     onPackagingSelected = viewModel::onPackagingSelected
// )
// ...