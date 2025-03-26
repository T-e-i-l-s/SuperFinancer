package com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.newsList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.LoadingState
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.R
import com.mustafin.main_menu_feature.presentation.screens.homeScreen.views.skeletons.NewsListItemSkeleton

/* View списка новостей */
fun LazyListScope.newsListView(
    newsLoadingState: LoadingState,
    news: List<NewsModel>?,
    openFullNews: (NewsModel) -> Unit
) {
    when (newsLoadingState) {
        in listOf(LoadingState.LOADED, LoadingState.UPDATING) -> {
            news?.let { newsSafe ->
                items(newsSafe, key = { it.link }) { newsItem ->
                    NewsView(newsItem) { openFullNews(newsItem) }
                }
            }
        }

        LoadingState.LOADING -> {
            items(5) {
                NewsListItemSkeleton()
            }
        }

        else -> {
            item {
                Text(
                    text = stringResource(id = R.string.loading_error),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(id = R.color.gray),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
