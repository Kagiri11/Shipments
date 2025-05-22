package com.cmaina.shipments.domain.model

import java.util.Date
import java.util.UUID

data class Shipment(
    val id: String = UUID.randomUUID().toString(),
    val title: String, // e.g., "Arriving today!"
    val trackingNumber: String, // e.g., "#NEJ20089934122231"
    val origin: String, // e.g., "Atlanta"
    val fullDescription: String, // Assembled description for display
    val status: ShipmentStatus,
    val estimatedArrivalDate: Date, // Or a String if you prefer to handle formatting elsewhere
    val price: Double,
    val currency: String, // e.g., "USD"
)