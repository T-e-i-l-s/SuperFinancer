package com.mustafin.main_menu_feature.data.mappers

import com.mustafin.core.data.source.local.newsSource.NewsEntity
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.network.data.source.newsApi.SingleNewResponseModel
import java.time.OffsetDateTime
import java.time.ZoneId

/* Мапперы моделей данных новостей */

fun SingleNewResponseModel.mapToNewsModel(): NewsModel {
    val offsetDateTime = OffsetDateTime.parse(this.publishedDate)
    val moscowZoneId = ZoneId.of("Europe/Moscow")
    val localDateTimeInMoscow = offsetDateTime.atZoneSameInstant(moscowZoneId).toLocalDateTime()

    return NewsModel(
        this.title,
        this.abstract,
        this.url,
        this.multimedia.find { it.height < it.width && it.height > 200 }?.url,
        this.source,
        localDateTimeInMoscow
    )
}

fun NewsEntity.mapToNewsModel(): NewsModel {
    return NewsModel(
        this.title,
        this.description,
        this.link,
        this.imageUrl,
        this.source,
        this.publishedAt
    )
}

fun NewsModel.mapToNewsEntity(): NewsEntity {
    return NewsEntity(
        title = this.title,
        description = this.description,
        link = this.link,
        imageUrl = this.imageUrl,
        source = this.source,
        publishedAt = this.publishedAt
    )
}