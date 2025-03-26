package com.mustafin.main_menu_feature.presentation.navigation.navGraph

import kotlinx.serialization.Serializable

/* Экраны в главном меню */
sealed class Destinations {
    @Serializable
    data object HomeScreen : Destinations()

    @Serializable
    data object BudgetScreen : Destinations()

    @Serializable
    data object FeedScreen : Destinations()

    @Serializable
    data object FavoritesScreen : Destinations()

    @Serializable
    data class FullNewsScreen(
        val newsName: String,
        val newsImageUrl: String?,
        val newsUrl: String
    ) : Destinations()

    @Serializable
    data class CreateEventScreen(
        val newsName: String? = null,
        val newsImageUrl: String? = null,
        val newsUrl: String? = null
    ) : Destinations()
}