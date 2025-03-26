package com.mustafin.search_feature.presentation.screens.searchScreen.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.search_feature.R
import com.mustafin.search_feature.utils.SearchState
import com.mustafin.search_feature.utils.search.AISearchResultModel
import com.mustafin.search_feature.utils.search.SearchResultModel

/* View со всеми результатами поиска */
@Composable
fun SearchResultsView(
    searchQuery: String,
    searchResults: SearchResultModel?,
    aiSearchState: SearchState,
    aiSearchResults: AISearchResultModel?
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        searchResults?.let { searchResultsSafe ->
            item {
                InternetSearchButton(query = searchQuery)

                Spacer(modifier = Modifier.height(16.dp))
            }
            
            item { 
                AISearchView(searchState = aiSearchState, searchResults = aiSearchResults)

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (searchResultsSafe.tickers.isNotEmpty()) {
                items(searchResultsSafe.tickers) { ticker ->
                    TickerView(ticker = ticker)
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (searchResultsSafe.news.isNotEmpty()) {
                item {
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

                items(searchResultsSafe.news) { news ->
                    NewsView(
                        news = news,
                        onClick = {
                            // Intent для открытия веб-страницы через браузер
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
                            context.startActivity(intent)
                        }
                    )
                }
            }

            if (searchResultsSafe.tickers.isEmpty() && searchResultsSafe.news.isEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.nothing_found),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.gray),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}