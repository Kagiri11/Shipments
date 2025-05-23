// File: com/cmaina/shipments/ui/screens/calculate/CalculateScreenUiState.kt
package com.cmaina.shipments.ui.screens.calculate

// Enum to identify different form fields for validation or state management
enum class CalculateFormField {
    SENDER_LOCATION,
    RECEIVER_LOCATION,
    APPROX_WEIGHT,
    PACKAGING,
    CATEGORIES
}

data class CalculateScreenUiState(
    // Input field values
    val senderLocation: String = "",
    val receiverLocation: String = "",
    val approxWeight: String = "", // Will be parsed to a number in ViewModel

    // Packaging options and selection
    val packagingOptions: List<String> = listOf("Box", "Envelope", "Pallet", "Custom"),
    val selectedPackaging: String = packagingOptions.firstOrNull() ?: "",

    // Category options and selections
    val categoryOptions: List<String> = listOf(
        "Documents", "Glass", "Liquid", "Food",
        "Electronics", "Product", "Others"
    ),
    val selectedCategories: Set<String> = emptySet(), // Allows multiple category selections

    // State flags
    val isCalculating: Boolean = false, // True when "Calculate" is pressed and processing

    // Result and error handling
    val calculationResult: String? = null, // Could be a more structured type later
    val fieldErrors: Map<CalculateFormField, String> = emptyMap(), // To show errors for specific fields
    val generalErrorMessage: String? = null // For errors not specific to a field
)