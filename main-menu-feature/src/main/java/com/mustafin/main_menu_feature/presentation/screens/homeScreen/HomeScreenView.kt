package com.mustafin.main_menu_feature.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.core.utils.LoadingState
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.newsList.newsListView
import com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.tickersList.TickersListView
import com.mustafin.ui_components.presentation.inputs.SearchBarView

/* View главного экрана приложения */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenView(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToSearchScreen: () -> Unit,
    openFullNews: (NewsModel) -> Unit,
) {
    val tickersLoadingState = viewModel.tickersLoadingState.collectAsStateWithLifecycle()
    val tickersList = viewModel.tickersList.collectAsStateWithLifecycle()
    val newsLoadingState = viewModel.newsLoadingState.collectAsStateWithLifecycle()
    val news = viewModel.news.collectAsStateWithLifecycle()

    val isRefreshing = tickersLoadingState.value == LoadingState.LOADING &&
            newsLoadingState.value in listOf(LoadingState.UPDATING, LoadingState.LOADING)
    val pullToRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = viewModel::loadAllData)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .pullRefresh(pullToRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullToRefreshState,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1f),
            backgroundColor = colorResource(id = R.color.background),
            contentColor = colorResource(id = R.color.content)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.padding(top = 16.dp))

                SearchBarView(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .clickable(onClick = navigateToSearchScreen),
                    isEnabled = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                TickersListView(
                    tickersList = tickersList.value,
                    loadingState = tickersLoadingState.value,
                    reloadTickers = viewModel::loadTickets
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.news),
                    style = MaterialTheme.typography.displayLarge,
                    color = colorResource(id = R.color.content),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            newsListView(
                newsLoadingState = newsLoadingState.value,
                news = news.value,
                openFullNews = openFullNews
            )
        }
    }
}