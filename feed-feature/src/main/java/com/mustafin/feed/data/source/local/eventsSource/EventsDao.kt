package com.mustafin.feed.data.source.local.eventsSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafin.feed.data.source.local.database.FeedDatabaseTables

/* Dao-интерфейс таблицы с событиями */
@Dao
interface EventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventsEntity)

    @Query("SELECT * FROM ${FeedDatabaseTables.EVENTS_TABLE} ORDER BY id DESC")
    suspend fun getAllEvents(): List<EventsEntity>

    @Query("SELECT * FROM ${FeedDatabaseTables.EVENTS_TABLE} WHERE id IN (:ids) ORDER BY id DESC")
    suspend fun getEventsByIds(ids: List<Int>): List<EventsEntity>
}