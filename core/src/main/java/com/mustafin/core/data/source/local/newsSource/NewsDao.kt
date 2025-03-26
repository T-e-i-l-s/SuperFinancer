package com.mustafin.core.data.source.local.newsSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.core.data.source.local.database.Tables

/* Dao-интерфейс для работы с таблицей новостей */
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("DELETE FROM ${Tables.NEWS_TABLE}")
    suspend fun clearTable()

    @Query("SELECT * FROM ${Tables.NEWS_TABLE}")
    suspend fun getAllNews(): List<NewsEntity>
}