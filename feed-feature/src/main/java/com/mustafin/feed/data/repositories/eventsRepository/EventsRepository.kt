package com.mustafin.feed.data.repositories.eventsRepository

import com.mustafin.feed.utils.events.EventModel

interface EventsRepository {
    suspend fun getAllEvents(): List<EventModel>
    suspend fun addEvent(event: EventModel)
}