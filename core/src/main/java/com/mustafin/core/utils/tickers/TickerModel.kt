package com.mustafin.core.utils.tickers

/* Модель информации о тикете */
data class TickerModel(
    val symbol: String,
    val name: String,
    val logo: String,
    val cost: Float,
    val isRising: Boolean,
    val difference: Float,
    val percentDifference: Float
)
