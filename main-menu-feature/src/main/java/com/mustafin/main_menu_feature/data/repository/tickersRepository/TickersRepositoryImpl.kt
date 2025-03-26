package com.mustafin.main_menu_feature.data.repository.tickersRepository

import com.mustafin.core.domain.exceptions.FailedToLoadDataException
import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.main_menu_feature.data.mappers.tickerCostAndInfoToTickerModel
import com.mustafin.network.data.source.tickersApi.TickersApiService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import javax.inject.Named

/* Репозиторий для работы с тикерами */
class TickersRepositoryImpl @Inject constructor(
    @Named("tickers") private val tickersList: List<String>,
    private val tickersApiService: TickersApiService
) : TickersRepository {
    /*
    Функция получения информации о тикетах.
    Как работает:
    1. Получает список нужных тикеров из tickers.json(находится в main-menu-feature/src/main/assets).
    2. Получает цену и информацию о каждом из нужных тикеров(в двух раздельных структурах).
    3. Объединяет информации в TickerModel.
    Все запросы происходят параллельно.
    */
    override suspend fun getTickersInfo(): Result<List<TickerModel>> {
        return try {
            coroutineScope {
                val deferredTickers = tickersList.map { tickerSymbol ->
                    async {
                        val ticketCostDeferred = async {
                            tickersApiService.getTickerCost(tickerSymbol).getOrThrow()
                        }
                        val ticketInfoDeferred = async {
                            tickersApiService.getTickerInfo(tickerSymbol).getOrThrow()
                        }

                        tickerCostAndInfoToTickerModel(
                            tickerSymbol,
                            ticketCostDeferred.await(),
                            ticketInfoDeferred.await()
                        )
                    }
                }

                val tickers = deferredTickers.awaitAll()

                Result.success(tickers)
            }
        } catch (e: Exception) {
            Result.failure(FailedToLoadDataException())
        }
    }
}