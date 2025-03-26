package com.mustafin.search_feature.mappers

import com.mustafin.network.data.source.tickersApi.GetTickerCostResponseModel
import com.mustafin.network.data.source.tickersApi.GetTickerInfoResponseModel
import com.mustafin.search_feature.data.mappers.tickerCostAndInfoToSearchTickerModel
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/* Тесты маппера тикеров */
class TickersMapperTest {
    // Тест с положительными изменениями курса
    @Test
    fun `test tickerCostAndInfoToSearchTickerModel should map correct data to SearchTickerModel`() {
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 100f,
            change = 5f,
            percentChange = 5f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Test Ticker",
            logo = "https://example.com/logo.png"
        )

        val result = tickerCostAndInfoToSearchTickerModel("TEST", ticketCost, ticketInfo)

        assertEquals("TEST", result.symbol, "TEST")
        assertEquals("Test Ticker", result.name, "Test Ticker")
        assertEquals("https://example.com/logo.png", result.logo, "https://example.com/logo.png")
        assertEquals(result.cost, 100f)
        assertTrue(result.isRising)
        assertEquals(result.percentDifference, 5f)
    }

    // Тест с отрицательными значениями изменений курса
    @Test
    fun `test tickerCostAndInfoToSearchTickerModel should handle negative change correctly`() {
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 100f,
            change = -5f,
            percentChange = -5f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Test Ticker",
            logo = "https://example.com/logo.png"
        )

        val result = tickerCostAndInfoToSearchTickerModel("TEST", ticketCost, ticketInfo)

        assertEquals("TEST", result.symbol)
        assertEquals("Test Ticker", result.name)
        assertEquals("https://example.com/logo.png", result.logo)
        assertEquals(result.cost, 100f)
        assertTrue(!result.isRising)
        assertEquals(result.percentDifference, 5f)
    }

    // Тест с нулевыми значениями изменений курса
    @Test
    fun `test tickerCostAndInfoToSearchTickerModel should handle zero change correctly`() {
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 100f,
            change = 0f,
            percentChange = 0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Test Ticker",
            logo = "https://example.com/logo.png"
        )

        val result = tickerCostAndInfoToSearchTickerModel("TEST", ticketCost, ticketInfo)

        assertEquals("TEST", result.symbol)
        assertEquals("Test Ticker", result.name)
        assertEquals("https://example.com/logo.png", result.logo)
        assertEquals(result.cost, 100f)
        assertTrue(result.isRising)
        assertEquals(result.percentDifference, 0f)
    }
}