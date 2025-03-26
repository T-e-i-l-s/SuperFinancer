package com.mustafin.main_menu_feature.di

import android.content.Context
import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsDao
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsDao
import com.mustafin.core.data.source.local.database.AppDatabase
import com.mustafin.core.data.source.local.newsSource.NewsDao
import com.mustafin.main_menu_feature.data.repository.budgetRepository.BudgetRepository
import com.mustafin.main_menu_feature.data.repository.budgetRepository.BudgetRepositoryImpl
import com.mustafin.main_menu_feature.data.repository.newsRepository.NewsRepository
import com.mustafin.main_menu_feature.data.repository.newsRepository.NewsRepositoryImpl
import com.mustafin.main_menu_feature.data.repository.tickersRepository.TickersRepository
import com.mustafin.main_menu_feature.data.repository.tickersRepository.TickersRepositoryImpl
import com.mustafin.network.data.source.newsApi.NewsApiService
import com.mustafin.network.data.source.tickersApi.TickersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.json.JSONArray
import java.io.InputStreamReader
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainMenuModule {

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }

    @Provides
    @Singleton
    fun provideBudgetOperationsDao(appDatabase: AppDatabase): BudgetOperationsDao {
        return appDatabase.budgetOperationsDao()
    }

    @Provides
    @Singleton
    fun provideBudgetGoalsDao(appDatabase: AppDatabase): BudgetGoalsDao {
        return appDatabase.budgetGoalsDao()
    }


    /* Репозитории */
    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(newsApiService, newsDao)
    }

    @Provides
    @Singleton
    fun provideTickersRepository(
        @Named("tickers") tickersList: List<String>,
        tickersApiService: TickersApiService
    ): TickersRepository {
        return TickersRepositoryImpl(tickersList, tickersApiService)
    }

    @Provides
    @Singleton
    fun provideBudgetRepository(
        budgetGoalsDao: BudgetGoalsDao,
        budgetOperationsDao: BudgetOperationsDao
    ): BudgetRepository {
        return BudgetRepositoryImpl(budgetGoalsDao, budgetOperationsDao)
    }


    /* Список тикетов(берется из json файла в assets модуля) */
    @Provides
    @Singleton
    @Named("tickers")
    fun provideTickers(@ApplicationContext context: Context): List<String> {
        val assetManager = context.assets
        val inputStream = assetManager.open("tickers.json")
        val json = InputStreamReader(inputStream).readText()
        val jsonArray = JSONArray(json)

        val tickers = mutableListOf<String>()
        for (i in 0 until jsonArray.length()) {
            tickers.add(jsonArray.getString(i))
        }
        return tickers
    }
}