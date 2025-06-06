package com.cmaina.shipments.domain.model

enum class ShipmentFilterType(val displayName: String) {
    ALL("All"),
    COMPLETED("Completed"),
    IN_PROGRESS("In progress"),
    PENDING("Pending");
}