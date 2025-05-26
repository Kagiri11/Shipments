package com.cmaina.shipments.utils

import com.cmaina.shipments.domain.model.Shipment
import com.cmaina.shipments.domain.model.ShipmentStatus
import java.util.Calendar
import java.util.Date

// For previewing or initial testing
fun getSampleShipments(): List<Shipment> {
    val today = Date() // Current date
    val calendar = Calendar.getInstance()
    calendar.set(2023, Calendar.SEPTEMBER, 20)
    val specificDate = calendar.time

    return listOf(
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122231",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            status = ShipmentStatus.IN_PROGRESS,
            estimatedArrivalDate = specificDate,
            price = 1400.00,
            currency = "USD"
        ),
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122232",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122232 from Atlanta, is arriving today!",
            status = ShipmentStatus.PENDING,
            estimatedArrivalDate = specificDate,
            price = 650.00,
            currency = "USD"
        ),
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122231",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            status = ShipmentStatus.IN_PROGRESS,
            estimatedArrivalDate = specificDate,
            price = 1400.00,
            currency = "USD"
        ),
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122231",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            status = ShipmentStatus.IN_PROGRESS,
            estimatedArrivalDate = specificDate,
            price = 1400.00,
            currency = "USD"
        ),
        Shipment(
            title = "Delivered Yesterday",
            trackingNumber = "#NEJ20089934122233",
            origin = "New York",
            fullDescription = "Your delivery, #NEJ20089934122233 from New York, was delivered.",
            status = ShipmentStatus.COMPLETED,
            estimatedArrivalDate = Date(today.time - (1000 * 60 * 60 * 24)), // Yesterday
            price = 75.50,
            currency = "USD"
        ),
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122231",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            status = ShipmentStatus.IN_PROGRESS,
            estimatedArrivalDate = specificDate,
            price = 1400.00,
            currency = "USD"
        ),
        Shipment(
            title = "Arriving today!",
            trackingNumber = "#NEJ20089934122231",
            origin = "Atlanta",
            fullDescription = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
            status = ShipmentStatus.IN_PROGRESS,
            estimatedArrivalDate = specificDate,
            price = 1400.00,
            currency = "USD"
        ),
        Shipment(
            title = "Delivered Yesterday",
            trackingNumber = "#NEJ20089934122233",
            origin = "New York",
            fullDescription = "Your delivery, #NEJ20089934122233 from New York, was delivered.",
            status = ShipmentStatus.COMPLETED,
            estimatedArrivalDate = Date(today.time - (1000 * 60 * 60 * 24)), // Yesterday
            price = 75.50,
            currency = "USD"
        )
    )
}