package com.cmaina.shipments.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cmaina.shipments.ui.navigation.Screen.Calculate
import com.cmaina.shipments.ui.navigation.Screen.History
import com.cmaina.shipments.ui.navigation.Screen.Home
import com.cmaina.shipments.ui.navigation.Screen.Profile
import com.cmaina.shipments.ui.theme.ShipmentsGrey
import com.cmaina.shipments.ui.theme.ShipmentsPurple
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

val TopLevelScreens: List<Screen> = listOf(Home, Calculate, History, Profile)

@Composable
fun ShipmentsBottomNavigation(
    navController: NavHostController
) {
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = ShipmentsSmokeWhite,
    ) {
        TopLevelScreens.forEach { screen ->
            AddBottomNavigationItem(
                screen = screen,
                currentScreen = currentScreen,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddBottomNavigationItem(
    screen: Screen,
    currentScreen: String?,
    navController: NavHostController
) {
    val isSelected = currentScreen == screen::class.qualifiedName
    val contentColor by animateColorAsState(if (isSelected) ShipmentsPurple else ShipmentsGrey)
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            if(currentScreen != screen::class.qualifiedName){
                navController.navigateToTopScreens(screen)
            }
        },
        icon = {
            Icon(
                tint = contentColor,
                imageVector = screen.icon,
                contentDescription = screen.label
            )
        },
        label = {
            Text(text = screen.label, color = contentColor)
        }
    )
}

fun NavHostController.navigateToTopScreens(screen: Screen) {
    this.navigate(screen) {
        popUpTo(this@navigateToTopScreens.graph.startDestinationRoute!!)
        launchSingleTop = true
    }
}