package com.cmaina.shipments.ui.model

import com.cmaina.shipments.domain.model.ShipmentFilterType

data class TabItem(
    val type: ShipmentFilterType,
    val count: Int
)