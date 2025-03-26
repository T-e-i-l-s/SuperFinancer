package com.mustafin.network.data.source.newsApi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* Модели ответов api новостей */

@Serializable
data class GetAllNewsResponseModel(
    val results: List<SingleNewResponseModel>
)

@Serializable
data class SingleNewResponseModel(
    val title: String,
    val abstract: String,
    val url: String,
    val source: String,
    @SerialName("published_date") val publishedDate: String,
    val multimedia: List<MultimediaItemResponseModel>
)

@Serializable
data class MultimediaItemResponseModel(
    val url: String,
    val height: Int,
    val width: Int
)


@Serializable
data class SearchNewsResponseModel(
    val response: FoundNewsResponseModel
)

@Serializable
data class FoundNewsResponseModel(
    val docs: List<SingleFoundNewResponseModel>
)

@Serializable
data class SingleFoundNewResponseModel(
    val abstract: String,
    @SerialName("web_url")val url: String
)