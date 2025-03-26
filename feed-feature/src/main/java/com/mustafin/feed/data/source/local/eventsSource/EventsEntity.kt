package com.mustafin.feed.data.source.local.eventsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.feed.data.source.local.database.FeedDatabaseTables
import com.mustafin.feed.utils.events.LinkedNewsModel
import com.mustafin.feed.utils.events.Tags

@Entity(tableName = FeedDatabaseTables.EVENTS_TABLE)
data class EventsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val images: List<String>,
    val text: String,
    val tags: List<Tags>,
    val linkedNews: LinkedNewsModel?
)
