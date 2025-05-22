package com.cmaina.shipments.domain.model.tracking
data class ActiveTrackingSummary(
    val shipmentNumber: String,       // e.g., "NEJ20089934122231"
    val vehicleIconUrl: String?,      // URL for the small vehicle icon (e.g., forklift)
    val sender: TrackingParty,
    val receiver: TrackingParty,
    val deliveryTimeEstimate: String, // e.g., "• 2 day -3 days" (the dot can be added in UI)
    val statusDescription: String,    // e.g., "Waiting to collect"
    val allowAddStopFeature: Boolean  // To determine if "Add Stop" button is relevant/active
)