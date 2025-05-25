package com.cmaina.shipments.ui.screens.calculate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.shipments.ui.screens.calculate.components.CalculateFormField
import com.cmaina.shipments.ui.screens.calculate.components.CalculateScreenUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculateViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculateScreenUiState())
    val uiState: StateFlow<CalculateScreenUiState> = _uiState.asStateFlow()

    // --- Event Handlers for UI Interactions ---

    fun onSenderLocationChanged(location: String) {
        _uiState.update {
            it.copy(
                senderLocation = location,
                fieldErrors = it.fieldErrors - CalculateFormField.SENDER_LOCATION, // Clear error on change
                calculationResult = null // Clear previous result
            )
        }
    }

    fun onReceiverLocationChanged(location: String) {
        _uiState.update {
            it.copy(
                receiverLocation = location,
                fieldErrors = it.fieldErrors - CalculateFormField.RECEIVER_LOCATION,
                calculationResult = null
            )
        }
    }

    fun onApproxWeightChanged(weight: String) {
        // Allow only digits and optionally a single decimal point for weight
        if (weight.isEmpty() || weight.matches(Regex("^\\d*\\.?\\d*\$"))) {
            _uiState.update {
                it.copy(
                    approxWeight = weight,
                    fieldErrors = it.fieldErrors - CalculateFormField.APPROX_WEIGHT,
                    calculationResult = null
                )
            }
        }
    }

    fun onPackagingSelected(packaging: String) {
        _uiState.update {
            it.copy(
                selectedPackaging = packaging,
                fieldErrors = it.fieldErrors - CalculateFormField.PACKAGING,
                calculationResult = null
            )
        }
    }

    fun onCategoryToggled(category: String) {
        _uiState.update { currentState ->
            val updatedCategories = currentState.selectedCategories.toMutableSet()
            if (updatedCategories.contains(category)) {
                updatedCategories.remove(category)
            } else {
                updatedCategories.add(category)
            }
            currentState.copy(
                selectedCategories = updatedCategories,
                fieldErrors = currentState.fieldErrors - CalculateFormField.CATEGORIES,
                calculationResult = null
            )
        }
    }

    fun onCalculateClicked() {
        if (validateInputs()) {
            viewModelScope.launch {
                _uiState.update { it.copy(isCalculating = true, generalErrorMessage = null, calculationResult = null) }
                // Simulate calculation delay
                delay(2000)
                // --- Actual calculation logic would go here ---
                // For now, let's just simulate a result or an error
                val success = true // Simulate success/failure
                if (success) {
                    val currentWeight = _uiState.value.approxWeight.toDoubleOrNull() ?: 0.0
                    val cost = 10.0 + (currentWeight * 0.5) + (_uiState.value.selectedCategories.size * 2.0)
                    _uiState.update {
                        it.copy(
                            isCalculating = false,
                            calculationResult = "Estimated cost: $${String.format("%.2f", cost)}"
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isCalculating = false,
                            generalErrorMessage = "Could not calculate. Please try again."
                        )
                    }
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val errors = mutableMapOf<CalculateFormField, String>()
        val currentState = _uiState.value

        if (currentState.senderLocation.isBlank()) {
            errors[CalculateFormField.SENDER_LOCATION] = "Sender location is required"
        }
        if (currentState.receiverLocation.isBlank()) {
            errors[CalculateFormField.RECEIVER_LOCATION] = "Receiver location is required"
        }
        if (currentState.approxWeight.isBlank()) {
            errors[CalculateFormField.APPROX_WEIGHT] = "Approximate weight is required"
        } else if (currentState.approxWeight.toDoubleOrNull() == null || currentState.approxWeight.toDouble() <= 0) {
            errors[CalculateFormField.APPROX_WEIGHT] = "Please enter a valid positive weight"
        }
        if (currentState.selectedPackaging.isBlank()) { // Should not happen if initialized properly
            errors[CalculateFormField.PACKAGING] = "Packaging type is required"
        }
        if (currentState.selectedCategories.isEmpty()) {
            errors[CalculateFormField.CATEGORIES] = "At least one category is required"
        }

        _uiState.update { it.copy(fieldErrors = errors, generalErrorMessage = null) }
        return errors.isEmpty()
    }

    fun clearErrorMessage(field: CalculateFormField? = null) {
        if (field != null) {
            _uiState.update { it.copy(fieldErrors = it.fieldErrors - field) }
        } else {
            _uiState.update { it.copy(generalErrorMessage = null) }
        }
    }
}