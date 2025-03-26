package com.mustafin.search_feature.presentation.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.search_feature.R
import com.mustafin.search_feature.presentation.screens.searchScreen.views.SearchResultsView
import com.mustafin.search_feature.utils.SearchState
import com.mustafin.ui_components.presentation.inputs.SearchBarView

/* View основного экрана поиска */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreenView(viewModel: SearchScreenViewModel = hiltViewModel()) {
    val searchState = viewModel.searchState.collectAsStateWithLifecycle()
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults = viewModel.searchResults.collectAsStateWithLifecycle()
    val aiSearchState = viewModel.aiSearchState.collectAsStateWithLifecycle()
    val aiSearchResults = viewModel.aiSearchResults.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    val isRefreshing = searchState.value == SearchState.UPDATING
    val pullToRefreshState =
        rememberPullRefreshState(refreshing = isRefreshing, onRefresh = viewModel::updateData)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarView(
            value = searchQuery.value,
            onValueChange = viewModel::setSearchQuery,
            search = {
                viewModel.loadNewData()
                keyboardController?.hide()
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        when (searchState.value) {
            SearchState.SEARCHING -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp),
                    trackColor = Color.Transparent,
                    color = colorResource(id = R.color.content),
                    strokeWidth = 2.dp
                )
            }

            in listOf(SearchState.COMPLETED, SearchState.UPDATING) -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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

                    SearchResultsView(
                        searchQuery = searchQuery.value,
                        searchResults = searchResults.value,
                        aiSearchState = aiSearchState.value,
                        aiSearchResults = aiSearchResults.value
                    )
                }
            }

            else -> {
                Text(
                    text = stringResource(id = R.string.input_your_query),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}