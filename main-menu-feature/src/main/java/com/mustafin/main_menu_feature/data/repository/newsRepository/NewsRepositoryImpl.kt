package com.mustafin.main_menu_feature.data.repository.newsRepository

import com.mustafin.core.data.source.local.newsSource.NewsDao
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.data.mappers.mapToNewsEntity
import com.mustafin.main_menu_feature.data.mappers.mapToNewsModel
import com.mustafin.network.data.source.newsApi.NewsApiService
import javax.inject.Inject

/* Репозиторий для работы с новостями */
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) : NewsRepository {
    /*
    Функция получения новостей. Работает таким образом:
    1. Проверяем кеш и возвращаем если там что-то есть.
    2. Загружаем данные из сети.
    3. Обновляем данные в кеше.
    */
    override suspend fun getAllNews(
        loadCachedNews: (List<NewsModel>) -> Unit,
        loadCurrentNews: (List<NewsModel>) -> Unit,
        onLoadingError: (Exception) -> Unit
    ) {
        val cachedNews = newsDao.getAllNews()
        if (cachedNews.isNotEmpty()) {
            loadCachedNews(cachedNews.map { it.mapToNewsModel() })
        }

        try {
            val newsApiResponse = newsApiService.getAllNews().getOrThrow()
            val currentNews = newsApiResponse.results.map { it.mapToNewsModel() }

            loadCurrentNews(currentNews)

            newsDao.clearTable()
            newsDao.insertNews(currentNews.map { it.mapToNewsEntity() })
        } catch (e: Exception) {
            onLoadingError(e)
        }
    }
}