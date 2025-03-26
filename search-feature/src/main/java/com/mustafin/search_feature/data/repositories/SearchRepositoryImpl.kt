package com.mustafin.search_feature.data.repositories

import com.mustafin.network.data.source.llmApi.AskLLMRequestModel
import com.mustafin.network.data.source.llmApi.LLMApiService
import com.mustafin.network.data.source.llmApi.LLMMessageRequestModel
import com.mustafin.network.data.source.newsApi.NewsApiService
import com.mustafin.network.data.source.tickersApi.TickersApiService
import com.mustafin.network.domain.exceptions.FailedToFetchDataException
import com.mustafin.search_feature.SearchSettings
import com.mustafin.search_feature.data.mappers.mapToAiSearchResultModel
import com.mustafin.search_feature.data.mappers.mapToSearchNewsModel
import com.mustafin.search_feature.data.mappers.tickerCostAndInfoToSearchTickerModel
import com.mustafin.search_feature.utils.search.AISearchResultModel
import com.mustafin.search_feature.utils.search.SearchResultModel
import com.mustafin.search_feature.utils.search.SearchTickerModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/* Репозиторий поиска, объединяет в себе логику поиска и запросы */
class SearchRepositoryImpl @Inject constructor(
    private val tickersApiService: TickersApiService,
    private val newsApiService: NewsApiService,
    private val llmApiService: LLMApiService
) : SearchRepository {
    /* Функция поиска, запросы в ней выполняются параллельно через async await */
    override suspend fun search(query: String): SearchResultModel {
        // Ищем информацию о подходящих тикерах
        val searchTickersResult = tickersApiService.searchTickers(query)
        val tickers = searchTickersResult.getOrNull()?.result?.take(10) ?: emptyList()
        val foundTickers = getTickersInfo(tickers.map { it.symbol })

        // Ищем информацию о подходящих новостях
        val searchNewsResult = newsApiService.searchNews(query)
        val news = searchNewsResult.getOrNull()?.mapToSearchNewsModel() ?: emptyList()

        return SearchResultModel(foundTickers, news)
    }

    /* Функция поиска через ИИ */
    override suspend fun askAI(query: String): Result<AISearchResultModel> {
        val generatedIamToken = llmApiService.getIAMToken()
        return try {
            val result = llmApiService.askLLM(
                generatedIamToken.getOrThrow(),
                AskLLMRequestModel(
                    modelUri = SearchSettings.AI_SEARCH_MODEL_URI,
                    messages = listOf(
                        LLMMessageRequestModel(
                            "system",
                            SearchSettings.AI_SEARCH_PROMPT
                        ),
                        LLMMessageRequestModel(
                            "user",
                            query
                        )
                    )
                )
            )

            Result.success(result.getOrThrow().mapToAiSearchResultModel())
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }

    private suspend fun getTickersInfo(tickers: List<String>): List<SearchTickerModel> {
        return coroutineScope {
            tickers.map { symbol ->
                async {
                    getTickerInfo(symbol)
                }
            }.awaitAll().filterNotNull()
        }
    }

    private suspend fun getTickerInfo(symbol: String): SearchTickerModel? {
        return coroutineScope {
            val costDeferred = async { tickersApiService.getTickerCost(symbol).getOrNull() }
            val infoDeferred = async { tickersApiService.getTickerInfo(symbol).getOrNull() }

            val cost = costDeferred.await()
            val info = infoDeferred.await()

            return@coroutineScope if (cost != null && info != null) {
                tickerCostAndInfoToSearchTickerModel(symbol, cost, info)
            } else {
                null
            }
        }
    }
}