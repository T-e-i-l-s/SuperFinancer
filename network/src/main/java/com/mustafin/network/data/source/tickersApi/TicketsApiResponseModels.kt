package com.mustafin.network.data.source.tickersApi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* Модели ответов api тикеров */

@Serializable
data class GetTickerCostResponseModel(
    @SerialName("c") val currentPrice: Float,
    @SerialName("d") val change: Float,
    @SerialName("dp") val percentChange: Float
)

@Serializable
data class GetTickerInfoResponseModel(
    val name: String,
    val logo: String
)

@Serializable
data class SearchTickersResponseModel(
    val result: List<SingleTickerResponseModel>
)

@Serializable
data class SingleTickerResponseModel(
    val symbol: String
)
