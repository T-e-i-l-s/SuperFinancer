package com.mustafin.core.data.source.local.newsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.core.data.source.local.database.Tables
import java.time.LocalDateTime

@Entity(tableName = Tables.NEWS_TABLE)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val link: String,
    val imageUrl: String?,
    val source: String,
    val publishedAt: LocalDateTime
)