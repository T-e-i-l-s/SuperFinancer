package com.mustafin.feed.data.repositories.eventsRepository

import com.mustafin.feed.data.mappers.mapToEventModel
import com.mustafin.feed.data.mappers.mapToEventsEntity
import com.mustafin.feed.data.source.local.eventsSource.EventsDao
import com.mustafin.feed.utils.events.EventModel
import javax.inject.Inject

/* Репозиторий для работы с событиями */
class EventsRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao
) : EventsRepository {
    override suspend fun getAllEvents(): List<EventModel> {
        return eventsDao.getAllEvents().map { it.mapToEventModel() }
    }

    override suspend fun addEvent(event: EventModel) {
        eventsDao.insertEvent(event = event.mapToEventsEntity())
    }
}