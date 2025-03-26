package com.mustafin.main_menu_feature.data.repository.tickersRepository

import com.mustafin.core.utils.tickers.TickerModel

interface TickersRepository {
    suspend fun getTickersInfo(): Result<List<TickerModel>>
}