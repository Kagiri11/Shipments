package com.cmaina.shipments.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cmaina.shipments.ui.navigation.ShipmentsBottomNavigation
import com.cmaina.shipments.ui.navigation.ShipmentsNavigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            ShipmentsBottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        ShipmentsNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}