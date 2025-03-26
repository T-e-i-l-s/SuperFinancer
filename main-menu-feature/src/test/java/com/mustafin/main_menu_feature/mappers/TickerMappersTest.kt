package com.mustafin.main_menu_feature.mappers

import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.main_menu_feature.data.mappers.tickerCostAndInfoToTickerModel
import com.mustafin.network.data.source.tickersApi.GetTickerCostResponseModel
import com.mustafin.network.data.source.tickersApi.GetTickerInfoResponseModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TickerMappersTest {
    // Простой тест маппера для корректных данных
    @Test
    fun `tickerCostAndInfoToTickerModel test`() {
        val symbol = "BTCUSD"
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 50000.0f,
            change = 500.0f,
            percentChange = 1.0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url"
        )

        val expected = TickerModel(
            symbol = "BTCUSD",
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url",
            cost = 50000.0f,
            isRising = true,
            difference = 500.0f,
            percentDifference = 1.0f
        )

        assertEquals(tickerCostAndInfoToTickerModel(symbol, ticketCost, ticketInfo), expected)
    }

    // Тест с отрицательным изменением цены
    @Test
    fun `tickerCostAndInfoToTickerModel test with negative change`() {
        val symbol = "BTCUSD"
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 50000.0f,
            change = -500.0f,
            percentChange = -1.0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url"
        )

        val expected = TickerModel(
            symbol = "BTCUSD",
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url",
            cost = 50000.0f,
            isRising = false,
            difference = 500.0f,
            percentDifference = 1.0f
        )

        assertEquals(tickerCostAndInfoToTickerModel(symbol, ticketCost, ticketInfo), expected)
    }

    // Тест с нулевым изменением цены
    @Test
    fun `tickerCostAndInfoToTickerModel test with zero change`() {
        val symbol = "BTCUSD"
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 50000.0f,
            change = 0.0f,
            percentChange = 0.0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url"
        )

        val expected = TickerModel(
            symbol = "BTCUSD",
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url",
            cost = 50000.0f,
            isRising = true,
            difference = 0.0f,
            percentDifference = 0.0f
        )

        assertEquals(tickerCostAndInfoToTickerModel(symbol, ticketCost, ticketInfo), expected)
    }

    // Тест с пустыми значениями в тикере
    @Test
    fun `tickerCostAndInfoToTickerModel test with empty tickerInfo`() {
        val symbol = "BTCUSD"
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 50000.0f,
            change = 500.0f,
            percentChange = 1.0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "",
            logo = ""
        )

        val expected = TickerModel(
            symbol = "BTCUSD",
            name = "",
            logo = "",
            cost = 50000.0f,
            isRising = true,
            difference = 500.0f,
            percentDifference = 1.0f
        )

        assertEquals(tickerCostAndInfoToTickerModel(symbol, ticketCost, ticketInfo), expected)
    }

    // Тест с нулевой ценой и нулевым изменением
    @Test
    fun `tickerCostAndInfoToTickerModel test with zero price and zero change`() {
        val symbol = "BTCUSD"
        val ticketCost = GetTickerCostResponseModel(
            currentPrice = 0f,
            change = 0f,
            percentChange = 0f
        )
        val ticketInfo = GetTickerInfoResponseModel(
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url"
        )

        val expected = TickerModel(
            symbol = "BTCUSD",
            name = "Bitcoin",
            logo = "http://bitcoin.logo.url",
            cost = 0.0f,
            isRising = true,
            difference = 0.0f,
            percentDifference = 0.0f
        )

        assertEquals(tickerCostAndInfoToTickerModel(symbol, ticketCost, ticketInfo), expected)
    }
}
