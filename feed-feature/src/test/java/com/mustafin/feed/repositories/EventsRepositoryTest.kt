package com.mustafin.feed.repositories

import com.mustafin.feed.data.repositories.eventsRepository.EventsRepositoryImpl
import com.mustafin.feed.data.source.local.eventsSource.EventsDao
import com.mustafin.feed.data.source.local.eventsSource.EventsEntity
import com.mustafin.feed.utils.events.EventModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class EventsRepositoryTest {
    // Замоканый класс источника данных о событиях
    class EventsDaoMock : EventsDao {
        private val events = mutableListOf<EventsEntity>()

        override suspend fun insertEvent(event: EventsEntity) {
            events.add(event)
        }

        override suspend fun getAllEvents(): List<EventsEntity> {
            return events
        }

        override suspend fun getEventsByIds(ids: List<Int>): List<EventsEntity> {
            return events.filter { it.id in ids }
        }
    }

    // Тест функции добавления события и функции чтения всех событий
    @Test
    fun `test insert function`() = runBlocking {
        val eventsRepository = EventsRepositoryImpl(EventsDaoMock())

        eventsRepository.addEvent(EventModel(0, emptyList(), "", emptySet(), null))
        assertEquals(
            eventsRepository.getAllEvents(),
            listOf(EventModel(0, emptyList(), "", emptySet(), null))
        )

        eventsRepository.addEvent(EventModel(1, emptyList(), "", emptySet(), null))
        assertEquals(
            eventsRepository.getAllEvents(),
            listOf(
                EventModel(0, emptyList(), "", emptySet(), null),
                EventModel(1, emptyList(), "", emptySet(), null)
            )
        )
    }
}