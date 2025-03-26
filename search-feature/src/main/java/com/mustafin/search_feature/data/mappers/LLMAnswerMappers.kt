package com.mustafin.search_feature.data.mappers

import com.mustafin.network.data.source.llmApi.AskLLMResponseModel
import com.mustafin.search_feature.utils.search.AISearchResultModel

/* Мапперы для моделей данных новостей, которые используются в поиске */

fun AskLLMResponseModel.mapToAiSearchResultModel(): AISearchResultModel {
    return AISearchResultModel(result.alternatives[0].message.text)
}