package com.mustafin.main_menu_feature.presentation.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.navigation.navGraph.Destinations

/* Модель данных с информацией о каждой странице, которая должна отображаться на таб баре */
private data class BottomNavigationEntity<T : Any>(
    val labelRes: Int,
    val icon: Painter,
    val route: T
)

/* Вью панели навигации в приложении */
@Composable
fun BottomNavigationBar(navController: NavController) {
    // Список экранов, которые должны отображаться на таб баре
    val tabs = listOf(
        BottomNavigationEntity(
            R.string.home_screen,
            painterResource(id = R.drawable.home),
            Destinations.HomeScreen
        ),
        BottomNavigationEntity(
            R.string.budget_screen,
            painterResource(id = R.drawable.credit_card),
            Destinations.BudgetScreen
        ),
        BottomNavigationEntity(
            R.string.feed_screen,
            painterResource(id = R.drawable.layer_three),
            Destinations.FeedScreen
        ),
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.secondary_background),
        contentColor = colorResource(id = R.color.gray)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        tabs.forEach { route ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(route.route::class)
            } == true

            val contentColor = when {
                isSelected -> colorResource(id = R.color.content)
                else -> colorResource(id = R.color.gray)
            }

            BottomNavigationItem(
                selected = isSelected,
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .navigationBarsPadding(),
                onClick = {
                    if (!isSelected) {
                        navController.navigate(route.route) {
                            launchSingleTop = true

                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = route.icon,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = contentColor
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = route.labelRes),
                        style = MaterialTheme.typography.labelSmall,
                        color = contentColor
                    )
                }
            )
        }
    }
}