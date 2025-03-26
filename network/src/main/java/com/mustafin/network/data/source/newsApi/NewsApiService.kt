package com.mustafin.network.data.source.newsApi

import com.mustafin.network.Keys
import com.mustafin.network.NetworkConstants
import com.mustafin.network.domain.exceptions.FailedToFetchDataException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/* Сервис для работы с api новостей */
class NewsApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    companion object {
        const val ALL_NEWS_ENDPOINT =
            "${NetworkConstants.NEWS_API_BASE_URL}/news/v3/content/all/all.json"
        const val SEARCH_NEWS_ENDPOINT =
            "${NetworkConstants.NEWS_API_BASE_URL}/search/v2/articlesearch.json"
    }

    suspend fun getAllNews(): Result<GetAllNewsResponseModel> {
        return try {
            val response = httpClient.get(ALL_NEWS_ENDPOINT) {
                parameter("api-key", Keys.NY_TIMES_API_KEY)
            }
            Result.success(response.body() as GetAllNewsResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }

    suspend fun searchNews(query: String): Result<SearchNewsResponseModel> {
        return try {
            val response = httpClient.get(SEARCH_NEWS_ENDPOINT) {
                parameter("api-key", Keys.NY_TIMES_API_KEY)
                parameter("q", query)
            }
            Result.success(response.body() as SearchNewsResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }
}