package com.mustafin.search_feature.data.mappers

import com.mustafin.network.data.source.tickersApi.GetTickerCostResponseModel
import com.mustafin.network.data.source.tickersApi.GetTickerInfoResponseModel
import com.mustafin.search_feature.utils.search.SearchTickerModel
import kotlin.math.abs

fun tickerCostAndInfoToSearchTickerModel(
    symbol: String,
    ticketCost: GetTickerCostResponseModel,
    ticketInfo: GetTickerInfoResponseModel
): SearchTickerModel {
    return SearchTickerModel(
        symbol,
        ticketInfo.name,
        ticketInfo.logo,
        ticketCost.currentPrice,
        ticketCost.change >= 0,
        abs(ticketCost.percentChange)
    )
}