package com.mustafin.main_menu_feature.data.mappers

import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.network.data.source.tickersApi.GetTickerCostResponseModel
import com.mustafin.network.data.source.tickersApi.GetTickerInfoResponseModel
import kotlin.math.abs

/* Мапперы моделей данных о тикерах */

fun tickerCostAndInfoToTickerModel(
    symbol: String,
    ticketCost: GetTickerCostResponseModel,
    ticketInfo: GetTickerInfoResponseModel
): TickerModel {
    return TickerModel(
        symbol,
        ticketInfo.name,
        ticketInfo.logo,
        ticketCost.currentPrice,
        ticketCost.change >= 0,
        abs(ticketCost.change),
        abs(ticketCost.percentChange)
    )
}