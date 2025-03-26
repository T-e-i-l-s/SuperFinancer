package com.mustafin.search_feature.di

import com.mustafin.network.data.source.llmApi.LLMApiService
import com.mustafin.network.data.source.newsApi.NewsApiService
import com.mustafin.network.data.source.tickersApi.TickersApiService
import com.mustafin.search_feature.data.repositories.SearchRepository
import com.mustafin.search_feature.data.repositories.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    @Provides
    @Singleton
    fun provideSearchRepository(
        tickersApiService: TickersApiService,
        newsApiService: NewsApiService,
        llmApiService: LLMApiService
    ): SearchRepository {
        return SearchRepositoryImpl(tickersApiService, newsApiService, llmApiService)
    }
}