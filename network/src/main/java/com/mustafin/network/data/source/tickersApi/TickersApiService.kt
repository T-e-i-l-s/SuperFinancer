package com.mustafin.network.data.source.tickersApi

import com.mustafin.network.Keys
import com.mustafin.network.NetworkConstants
import com.mustafin.network.domain.exceptions.FailedToFetchDataException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

/* Сервис апи для получения тикеров */
class TickersApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    companion object {
        const val TICKER_COST_ENDPOINT =
            "${NetworkConstants.TICKERS_API_BASE_URL}/quote"
        const val TICKER_INFO_ENDPOINT =
            "${NetworkConstants.TICKERS_API_BASE_URL}/stock/profile2"
        const val SEARCH_TICKER_ENDPOINT =
            "${NetworkConstants.TICKERS_API_BASE_URL}/search"
    }

    suspend fun getTickerCost(symbol: String): Result<GetTickerCostResponseModel> {
        return try {
            val response = httpClient.get(TICKER_COST_ENDPOINT) {
                parameter("symbol", symbol)
                parameter("token", Keys.FINNHUB_API_KEY)
            }
            Result.success(response.body() as GetTickerCostResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }

    suspend fun getTickerInfo(symbol: String): Result<GetTickerInfoResponseModel> {
        return try {
            val response = httpClient.get(TICKER_INFO_ENDPOINT) {
                parameter("symbol", symbol)
                parameter("token", Keys.FINNHUB_API_KEY)
            }
            Result.success(response.body() as GetTickerInfoResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }

    suspend fun searchTickers(query: String): Result<SearchTickersResponseModel> {
        return try {
            val response = httpClient.get(SEARCH_TICKER_ENDPOINT) {
                parameter("exchange", "US")
                parameter("q", query)
                parameter("token", Keys.FINNHUB_API_KEY)
            }
            Result.success(response.body() as SearchTickersResponseModel)
        } catch (e: Exception) {
            Result.failure(FailedToFetchDataException())
        }
    }
}