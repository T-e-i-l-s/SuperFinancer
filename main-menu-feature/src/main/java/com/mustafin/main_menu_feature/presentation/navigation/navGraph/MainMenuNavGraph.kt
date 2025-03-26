package com.mustafin.main_menu_feature.presentation.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mustafin.feed.presentation.screens.createEventScreen.CreateEventScreenView
import com.mustafin.feed.presentation.screens.favoritesScreen.FavoritesScreenView
import com.mustafin.feed.presentation.screens.feedScreen.FeedScreenView
import com.mustafin.feed.utils.events.LinkedNewsModel
import com.mustafin.main_menu_feature.presentation.screens.budgetScreen.BudgetScreenView
import com.mustafin.main_menu_feature.presentation.screens.fullNewsScreen.FullNewsScreen
import com.mustafin.main_menu_feature.presentation.screens.homeScreen.HomeScreenView

/* Граф навигации внутри главного меню */
@Composable
fun MainMenuNavGraph(
    navController: NavHostController,
    navigateToSearchScreen: () -> Unit,
) {
    NavHost(navController = navController, startDestination = Destinations.HomeScreen) {
        composable<Destinations.HomeScreen> {
            HomeScreenView(
                navigateToSearchScreen = navigateToSearchScreen,
                openFullNews = { navigateToFullNews(navController, it.title, it.imageUrl, it.link) }
            )
        }

        composable<Destinations.BudgetScreen> {
            BudgetScreenView()
        }

        composable<Destinations.FeedScreen> {
            FeedScreenView(
                navigateToFavoritesScreen = { navController.navigate(Destinations.FavoritesScreen) },
                navigateToCreateEventScreen = { navController.navigate(Destinations.CreateEventScreen()) },
                openNew = { navigateToFullNews(navController, it.title, it.imageUrl, it.link) }
            )
        }

        composable<Destinations.FavoritesScreen>(
            enterTransition = { NavigationAnimations.slideIn },
            exitTransition = { NavigationAnimations.slideOut }
        ) {
            FavoritesScreenView(
                popBackStack = { navController.popBackStack() },
                openNew = { navigateToFullNews(navController, it.title, it.imageUrl, it.link) }
            )
        }

        composable<Destinations.FullNewsScreen> { backStackEntry ->
            val navArgs: Destinations.FullNewsScreen = backStackEntry.toRoute()
            FullNewsScreen(
                url = navArgs.newsUrl,
                popBackNavigationStack = navController::popBackStack,
                navigateToCreateEventScreen = {
                    val target = Destinations.CreateEventScreen(
                        navArgs.newsName,
                        navArgs.newsImageUrl,
                        navArgs.newsUrl
                    )
                    navController.navigate(target)
                }
            )
        }

        composable<Destinations.CreateEventScreen>(
            enterTransition = { NavigationAnimations.slideIn },
            exitTransition = { NavigationAnimations.slideOut }
        ) { backStackEntry ->
            val navArgs: Destinations.CreateEventScreen = backStackEntry.toRoute()

            val linkedNews = navArgs.newsName?.let { newsNameSafe ->
                navArgs.newsUrl?.let { newsUrlSafe ->
                    LinkedNewsModel(
                        title = newsNameSafe,
                        link = newsUrlSafe,
                        imageUrl = navArgs.newsImageUrl
                    )
                }
            }

            CreateEventScreenView(
                linkedNews = linkedNews,
                popBackStack = { navController.popBackStack() },
                navigateToFeedScreen = { navController.navigate(Destinations.FeedScreen) }
            )
        }
    }
}

// Функция открывает экран с полной информацией о новости
fun navigateToFullNews(
    navController: NavHostController,
    title: String,
    imageUrl: String?,
    link: String
) {
    val target = Destinations.FullNewsScreen(title, imageUrl, link)
    navController.navigate(target)
}
