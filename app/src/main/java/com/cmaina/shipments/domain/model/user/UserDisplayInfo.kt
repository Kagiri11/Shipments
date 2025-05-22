package com.cmaina.shipments.domain.model.user

data class UserDisplayInfo(
    val profileImageUrl: String?, // URL for the user's avatar
    val currentLocation: String   // e.g., "Wertheimer, Illinois"
)