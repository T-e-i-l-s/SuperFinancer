package com.mustafin.search_feature.data.repositories

import com.mustafin.search_feature.utils.search.AISearchResultModel
import com.mustafin.search_feature.utils.search.SearchResultModel

interface SearchRepository {
    suspend fun search(query: String): SearchResultModel
    suspend fun askAI(query: String): Result<AISearchResultModel>
}