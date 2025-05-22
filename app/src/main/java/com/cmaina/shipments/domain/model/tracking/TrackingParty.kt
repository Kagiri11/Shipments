package com.cmaina.shipments.domain.model.tracking
data class TrackingParty(
    val city: String,        // e.g., "Atlanta" or "Chicago"
    val locationCode: String // e.g., "5243" or "6342" - could be a terminal, zip, etc.
)