package com.mustafin.search_feature.utils.search

/* Модель информации о тикере, которые отображаются при поиске */
data class SearchTickerModel(
    val symbol: String,
    val name: String,
    val logo: String,
    val cost: Float,
    val isRising: Boolean,
    val percentDifference: Float
)
