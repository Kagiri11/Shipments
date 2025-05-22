package com.cmaina.shipments.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.cmaina.shipments.ui.navigation.Screen.Calculate
import com.cmaina.shipments.ui.navigation.Screen.History
import com.cmaina.shipments.ui.navigation.Screen.Home
import com.cmaina.shipments.ui.navigation.Screen.Profile

val TopLevelScreens: List<Screen> = listOf(Home, Calculate, History, Profile)

@Composable
fun ShipmentsBottomNavigation(
    navController: NavHostController
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TopLevelScreens.forEach { screen ->
            AddBottomNavigationItem(
                screen = screen,
                currentScreen = navController.currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddBottomNavigationItem(
    screen: Screen,
    currentScreen: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentScreen?.hierarchy?.any { it.route == screen::class.qualifiedName } == true
    NavigationBarItem(
        selected = isSelected,
        onClick = {
            navController.navigateToTopScreens(screen)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.label
            )
        },
        label = {
            Text(text = screen.label)
        }
    )
}

fun NavHostController.navigateToTopScreens(screen: Screen) {
    this.navigate(screen) {
        popUpTo(this@navigateToTopScreens.graph.startDestinationRoute!!)
        launchSingleTop = true
    }
}