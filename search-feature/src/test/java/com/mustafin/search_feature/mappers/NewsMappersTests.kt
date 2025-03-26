package com.mustafin.search_feature.mappers

import com.mustafin.network.data.source.newsApi.FoundNewsResponseModel
import com.mustafin.network.data.source.newsApi.SearchNewsResponseModel
import com.mustafin.network.data.source.newsApi.SingleFoundNewResponseModel
import com.mustafin.search_feature.data.mappers.mapToSearchNewsModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

/* Тесты мапперов моделей новостей в модуле search */
class NewsMappersTests {
    // Тест на непустой список новостей
    @Test
    fun `simple mapping test`() {
        val response = SearchNewsResponseModel(
            response = FoundNewsResponseModel(
                docs = listOf(
                    SingleFoundNewResponseModel(
                        abstract = "Description 1",
                        url = "https://example.com/1"
                    ),
                    SingleFoundNewResponseModel(
                        abstract = "Description 2",
                        url = "https://example.com/2"
                    )
                )
            )
        )

        val result = response.mapToSearchNewsModel()

        assertEquals(
            "Description 1",
            result[0].description,
            "Description 1"
        )
        assertEquals(
            "https://example.com/1",
            result[0].link,
            "https://example.com/1"
        )
        assertEquals(
            "Description 2",
            result[1].description,
            "Description 2"
        )
        assertEquals(
            "https://example.com/2",
            result[1].link,
            "https://example.com/2"
        )
    }

    // Тест на пустой список новостей
    @Test
    fun `empty list mapping test`() {
        val response = SearchNewsResponseModel(
            response = FoundNewsResponseModel(docs = emptyList())
        )
        val result = response.mapToSearchNewsModel()
        assertTrue(result.isEmpty())
    }
}