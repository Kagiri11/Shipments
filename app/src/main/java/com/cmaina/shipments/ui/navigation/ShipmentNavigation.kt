package com.cmaina.shipments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cmaina.shipments.ui.navigation.Screen.Calculate
import com.cmaina.shipments.ui.navigation.Screen.History
import com.cmaina.shipments.ui.navigation.Screen.Home
import com.cmaina.shipments.ui.navigation.Screen.Profile
import com.cmaina.shipments.ui.navigation.Screen.Success
import com.cmaina.shipments.ui.screens.calculate.CalculateScreen
import com.cmaina.shipments.ui.screens.history.ShipmentHistoryScreen
import com.cmaina.shipments.ui.screens.home.HomeScreen
import com.cmaina.shipments.ui.screens.profile.ProfileScreen
import com.cmaina.shipments.ui.screens.success.SuccessScreen

@Composable
fun ShipmentsNavigation(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
){
    NavHost(modifier = modifier, navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }

        composable<Profile> {
            ProfileScreen()
        }

        composable<Calculate> {
            CalculateScreen()
        }

        composable<History> {
            ShipmentHistoryScreen()
        }

        composable<Success> {
            SuccessScreen()
        }
    }
}