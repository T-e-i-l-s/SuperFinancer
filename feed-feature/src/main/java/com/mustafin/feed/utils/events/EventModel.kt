package com.mustafin.feed.utils.events

/* Модель данных о событии в ленте */
data class EventModel(
    val id: Int = 0,
    val images: List<String>,
    val text: String,
    val tags: Set<Tags>,
    val linkedNews: LinkedNewsModel?
)
