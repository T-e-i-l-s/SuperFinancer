package com.mustafin.main_menu_feature.presentation.navigation.navGraph

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

/* Анимации, которые используются при навигации */
object NavigationAnimations {
    val slideIn = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(400)
    )

    val slideOut = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(400)
    )
}