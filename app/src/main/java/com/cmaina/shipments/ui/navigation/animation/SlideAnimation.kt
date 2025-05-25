package com.cmaina.shipments.ui.navigation.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

const val slideAnimationDuration = 250
const val fadeAnimationDuration = 280
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIn(towards: AnimatedContentTransitionScope.SlideDirection): EnterTransition {
    return fadeIn(animationSpec = tween(fadeAnimationDuration, easing = LinearEasing)
    ) + slideIntoContainer(
        animationSpec = tween(slideAnimationDuration, easing = EaseIn),
        towards = towards
    )
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOut(towards: AnimatedContentTransitionScope.SlideDirection): ExitTransition {
    return fadeOut(animationSpec = tween(fadeAnimationDuration, easing = LinearEasing)
    ) + slideOutOfContainer(
        animationSpec = tween(slideAnimationDuration, easing = EaseIn),
        towards = towards
    )
}