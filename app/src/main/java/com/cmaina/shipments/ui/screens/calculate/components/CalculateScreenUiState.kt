package com.cmaina.shipments.ui.screens.calculate.components

// Enum to identify different form fields for validation or state management
enum class CalculateFormField {
    SENDER_LOCATION,
    RECEIVER_LOCATION,
    APPROX_WEIGHT,
    PACKAGING,
    CATEGORIES
}

data class CalculateScreenUiState(
    val senderLocation: String = "",
    val receiverLocation: String = "",
    val approxWeight: String = "",

    val packagingOptions: List<String> = listOf("Box", "Envelope", "Pallet", "Custom"),
    val selectedPackaging: String = packagingOptions.firstOrNull() ?: "",

    val categoryOptions: List<String> = listOf(
        "Documents", "Glass", "Liquid", "Food",
        "Electronics", "Product", "Others"
    ),
    val selectedCategories: Set<String> = emptySet(),
    // State flags
    val isCalculating: Boolean = false,
    val navigateToSuccessScreen: Boolean = false,

    // Result and error handling
    val calculationResult: String? = null,
    val fieldErrors: Map<CalculateFormField, String> = emptyMap(),
    val generalErrorMessage: String? = null
)