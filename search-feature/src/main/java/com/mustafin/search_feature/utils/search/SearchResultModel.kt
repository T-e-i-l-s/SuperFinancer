package com.mustafin.search_feature.utils.search

/* Модель данных со всеми результатами поиска */
data class SearchResultModel(
    val tickers: List<SearchTickerModel>,
    val news: List<SearchNewsModel>
)
