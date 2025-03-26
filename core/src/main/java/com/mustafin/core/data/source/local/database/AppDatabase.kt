package com.mustafin.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsDao
import com.mustafin.core.data.source.local.budgetSources.goalsSource.BudgetGoalsEntity
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsDao
import com.mustafin.core.data.source.local.budgetSources.operationSource.BudgetOperationsEntity
import com.mustafin.core.data.source.local.newsSource.NewsDao
import com.mustafin.core.data.source.local.newsSource.NewsEntity
import com.mustafin.core.utils.typeConverters.BigDecimalConverters
import com.mustafin.core.utils.typeConverters.BudgetOperationTypeConverters
import com.mustafin.core.utils.typeConverters.LocalDateTimeConverters

/* Основная база данных приложения */
@Database(
    entities = [NewsEntity::class, BudgetOperationsEntity::class, BudgetGoalsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BigDecimalConverters::class,
    BudgetOperationTypeConverters::class,
    LocalDateTimeConverters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun budgetOperationsDao(): BudgetOperationsDao
    abstract fun budgetGoalsDao(): BudgetGoalsDao
}