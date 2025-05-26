package com.cmaina.shipments.ui.model

import com.cmaina.shipments.domain.model.ShipmentFilterType

data class TabItem(
    val type: ShipmentFilterType,
    val count: Int,
    val id: Int = type.ordinal
)

fun getSampleTabItems(): List<TabItem> {
    return listOf(
        TabItem(type = ShipmentFilterType.ALL, count = 12),
        TabItem(type = ShipmentFilterType.COMPLETED, count = 5),
        TabItem(type = ShipmentFilterType.IN_PROGRESS, count = 3),
        TabItem(type = ShipmentFilterType.PENDING, count = 4) // Assuming 'Pending' also has a count
    )
}