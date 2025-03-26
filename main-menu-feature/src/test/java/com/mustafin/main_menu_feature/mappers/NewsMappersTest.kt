package com.mustafin.main_menu_feature.mappers

import com.mustafin.core.data.source.local.newsSource.NewsEntity
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.main_menu_feature.data.mappers.mapToNewsEntity
import com.mustafin.main_menu_feature.data.mappers.mapToNewsModel
import com.mustafin.network.data.source.newsApi.SingleNewResponseModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeParseException

class NewsMappersTest {
    // Тест маппера с мультимедиа, где подходящее изображение отсутствует
    @Test
    fun `SingleNewResponseModel mapToNewsModel test with no suitable image`() {
        val singleNewResponseModel = SingleNewResponseModel(
            title = "Test Title",
            abstract = "Test Abstract",
            url = "http://test.url",
            publishedDate = "2025-02-21T10:00:00+03:00",
            source = "Test Source",
            multimedia = emptyList()
        )

        val expected = NewsModel(
            title = "Test Title",
            description = "Test Abstract",
            link = "http://test.url",
            imageUrl = null,
            source = "Test Source",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00")
                .atZoneSameInstant(ZoneId.of("Europe/Moscow")).toLocalDateTime()
        )

        assertEquals(singleNewResponseModel.mapToNewsModel(), expected)
    }

    // Тест маппера для NewsEntity -> NewsModel
    @Test
    fun `NewsEntity mapToNewsModel test`() {
        val newsEntity = NewsEntity(
            title = "Test Title",
            description = "Test Abstract",
            link = "http://test.url",
            imageUrl = "http://image.url",
            source = "Test Source",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00").toLocalDateTime()
        )

        val expected = NewsModel(
            title = "Test Title",
            description = "Test Abstract",
            link = "http://test.url",
            imageUrl = "http://image.url",
            source = "Test Source",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00").toLocalDateTime()
        )

        assertEquals(newsEntity.mapToNewsModel(), expected)
    }

    // Тест маппера для NewsModel -> NewsEntity
    @Test
    fun `NewsModel mapToNewsEntity test`() {
        val newsModel = NewsModel(
            title = "Test Title",
            description = "Test Abstract",
            link = "http://test.url",
            imageUrl = "http://image.url",
            source = "Test Source",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00").toLocalDateTime()
        )

        val expected = NewsEntity(
            title = "Test Title",
            description = "Test Abstract",
            link = "http://test.url",
            imageUrl = "http://image.url",
            source = "Test Source",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00").toLocalDateTime()
        )

        assertEquals(newsModel.mapToNewsEntity(), expected)
    }

    // Тест с некорректным форматом даты
    @Test(expected = DateTimeParseException::class)
    fun `SingleNewResponseModel mapToNewsModel test with invalid date format`() {
        val singleNewResponseModel = SingleNewResponseModel(
            title = "Test Title",
            abstract = "Test Abstract",
            url = "http://test.url",
            publishedDate = "Invalid Date",
            source = "Test Source",
            multimedia = emptyList()
        )

        singleNewResponseModel.mapToNewsModel()
    }

    // Тест с пустым объектом
    @Test
    fun `SingleNewResponseModel mapToNewsModel test with empty model`() {
        val singleNewResponseModel = SingleNewResponseModel(
            title = "",
            abstract = "",
            url = "",
            publishedDate = "2025-02-21T10:00:00+03:00",
            source = "",
            multimedia = emptyList()
        )

        val expected = NewsModel(
            title = "",
            description = "",
            link = "",
            imageUrl = null,
            source = "",
            publishedAt = OffsetDateTime.parse("2025-02-21T10:00:00+03:00")
                .atZoneSameInstant(ZoneId.of("Europe/Moscow")).toLocalDateTime()
        )

        assertEquals(singleNewResponseModel.mapToNewsModel(), expected)
    }
}
