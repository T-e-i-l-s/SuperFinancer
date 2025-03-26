package com.mustafin.main_menu_feature.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.navigation.navGraph.MainMenuNavGraph

/* Контейнер с таб навигацией для главного меню */
@Composable
fun MainMenuContainer(navigateToSearchScreen: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = colorResource(id = R.color.background),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            MainMenuNavGraph(navController, navigateToSearchScreen)
        }
    }
}