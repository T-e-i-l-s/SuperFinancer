package com.mustafin.network.di

import com.mustafin.network.data.source.llmApi.LLMApiService
import com.mustafin.network.data.source.newsApi.NewsApiService
import com.mustafin.network.data.source.tickersApi.TickersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    @Provides
    @Singleton
    fun provideNewsApiService(httpClient: HttpClient): NewsApiService {
        return NewsApiService(httpClient)
    }

    @Provides
    @Singleton
    fun provideTickersApiService(httpClient: HttpClient): TickersApiService {
        return TickersApiService(httpClient)
    }

    @Provides
    @Singleton
    fun provideLLMApiService(httpClient: HttpClient): LLMApiService {
        return LLMApiService(httpClient)
    }
}