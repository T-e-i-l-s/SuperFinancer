package com.mustafin.main_menu_feature.data.repository.newsRepository

import com.mustafin.core.utils.news.NewsModel

interface NewsRepository {
    suspend fun getAllNews(
        loadCachedNews: (List<NewsModel>) -> Unit,
        loadCurrentNews: (List<NewsModel>) -> Unit,
        onLoadingError: (Exception) -> Unit
    )
}