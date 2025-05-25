package com.cmaina.shipments.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import com.cmaina.shipments.ui.navigation.animation.slideIn
import com.cmaina.shipments.ui.navigation.animation.slideOut
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
        composable<Home>(
            enterTransition = {
                when (initialState.destination.route) {
                    in listOf(Calculate, History, Profile).map { it::class.qualifiedName } -> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    in listOf(Calculate, History, Profile).map { it::class.qualifiedName } -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    else -> ExitTransition.None
                }
            }
        ) {
            HomeScreen()
        }

        composable<Profile>(
            enterTransition = {
                when (initialState.destination.route) {
                    in listOf(Calculate, History, Home).map { it::class.qualifiedName } -> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    in listOf(Calculate, History, Home).map { it::class.qualifiedName } -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    else -> ExitTransition.None
                }
            }
        ) {
            ProfileScreen()
        }

        composable<Calculate>(
            enterTransition = {
                when (initialState.destination.route) {
                    Home::class.qualifiedName -> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    in listOf(History, Profile).map { it::class.qualifiedName }-> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Home::class.qualifiedName -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    in listOf(History, Profile).map { it::class.qualifiedName } -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    else -> ExitTransition.None
                }
            }
        ) {
            CalculateScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<History>(
            enterTransition = {
                when (initialState.destination.route) {
                    in listOf(Calculate, Home).map { it::class.qualifiedName } -> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    Profile::class.qualifiedName -> slideIn(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    else -> EnterTransition.None
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    in listOf(Calculate, Home).map { it::class.qualifiedName } -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.End
                    )

                    Profile::class.qualifiedName -> slideOut(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start
                    )

                    else -> ExitTransition.None
                }
            }
        ) {
            ShipmentHistoryScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Success> {
            SuccessScreen()
        }
    }
}