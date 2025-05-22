package com.cmaina.shipments.domain.model.search

data class ShipmentSearchResult(
    val id: String,                 // Unique ID for the search result
    val title: String,              // e.g., "Macbook pro M2"
    val shipmentNumber: String,     // e.g., "#NE43857340857904"
    val originCity: String,         // e.g., "Paris"
    val destinationCity: String     // e.g., "Morocco"
)