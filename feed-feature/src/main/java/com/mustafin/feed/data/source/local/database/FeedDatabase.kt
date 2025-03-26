package com.mustafin.feed.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mustafin.feed.data.source.local.eventsSource.EventsDao
import com.mustafin.feed.data.source.local.eventsSource.EventsEntity
import com.mustafin.feed.utils.converters.ImageUrisConverter
import com.mustafin.feed.utils.converters.LinkedNewsModelConverter
import com.mustafin.feed.utils.converters.TagsConverter

/* База данных модуля ленты */
@Database(entities = [EventsEntity::class], version = 1, exportSchema = false)
@TypeConverters(ImageUrisConverter::class, TagsConverter::class, LinkedNewsModelConverter::class)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
}