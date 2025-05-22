package com.cmaina.shipments.domain.model

enum class ShipmentStatus(val displayName: String) {
    IN_PROGRESS("In progress"),
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    LOADING("Loading"),
}