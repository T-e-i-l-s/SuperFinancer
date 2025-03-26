package com.mustafin.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mustafin.main_menu_feature.presentation.navigation.MainMenuContainer
import com.mustafin.search_feature.presentation.screens.searchScreen.SearchScreenView
import kotlinx.serialization.Serializable

/* Все вершины основного графа навигации */
object Destinations {
    @Serializable
    object MainMenu

    @Serializable
    object SearchScreen
}

/* Основной граф навигации приложения */
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.MainMenu
    ) {
        composable<Destinations.MainMenu> {
            MainMenuContainer(
                navigateToSearchScreen = { navController.navigate(Destinations.SearchScreen) }
            )
        }

        composable<Destinations.SearchScreen> {
            SearchScreenView()
        }
    }
}


