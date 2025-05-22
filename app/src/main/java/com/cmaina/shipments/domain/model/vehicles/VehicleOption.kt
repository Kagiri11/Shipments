package com.cmaina.shipments.domain.model.vehicles

data class VehicleOption(
    val id: String,                 // Unique identifier for the vehicle option
    val name: String,               // e.g., "Ocean freight"
    val description: String,        // e.g., "International"
    val imageUrl: String            // URL for the vehicle's image
)