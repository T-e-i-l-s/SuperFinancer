package com.mustafin.core.utils.news

import java.time.LocalDateTime

/* Модель данных о новости */
data class NewsModel(
    val title: String,
    val description: String,
    val link: String,
    val imageUrl: String?,
    val source: String,
    val publishedAt: LocalDateTime
)
