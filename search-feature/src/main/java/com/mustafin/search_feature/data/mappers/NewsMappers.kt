package com.mustafin.search_feature.data.mappers

import com.mustafin.network.data.source.newsApi.SearchNewsResponseModel
import com.mustafin.search_feature.utils.search.SearchNewsModel

/* Мапперы для моделей данных новостей, которые используются в поиске */

fun SearchNewsResponseModel.mapToSearchNewsModel(): List<SearchNewsModel> {
    val docs = this.response.docs

    return docs.map {
        SearchNewsModel(
            description = it.abstract,
            link = it.url
        )
    }
}