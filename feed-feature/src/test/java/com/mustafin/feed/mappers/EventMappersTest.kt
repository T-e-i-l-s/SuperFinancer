package com.mustafin.feed.mappers

import com.mustafin.feed.data.mappers.mapToEventModel
import com.mustafin.feed.data.mappers.mapToEventsEntity
import com.mustafin.feed.data.source.local.eventsSource.EventsEntity
import com.mustafin.feed.utils.events.EventModel
import com.mustafin.feed.utils.events.Tags
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EventMappersTest {
    // Тест маппинга из EventsEntity в EventModel
    @Test
    fun `entity to event model test`() {
        val entity = EventsEntity(
            0,
            listOf("a", "b", "c", "d"),
            "",
            listOf(Tags.Meal, Tags.Travel, Tags.Future, Tags.Education, Tags.Lifestyle),
            null
        )

        val mappingResult = entity.mapToEventModel()

        assertEquals(
            mappingResult,
            EventModel(
                0,
                listOf("a", "b", "c", "d"),
                "",
                setOf(Tags.Meal, Tags.Travel, Tags.Future, Tags.Education, Tags.Lifestyle),
                null
            )
        )
    }

    // Тест маппинга из EventModel в EventsEntity
    @Test
    fun `event model to entity test`() {
        val entity =EventModel(
            0,
            listOf("a", "b", "c", "d"),
            "",
            setOf(Tags.Meal, Tags.Travel, Tags.Future, Tags.Education, Tags.Lifestyle),
            null
        )

        val mappingResult = entity.mapToEventsEntity()

        assertEquals(
            mappingResult,
            EventsEntity(
                0,
                listOf("a", "b", "c", "d"),
                "",
                listOf(Tags.Meal, Tags.Travel, Tags.Future, Tags.Education, Tags.Lifestyle),
                null
            )
        )
    }
}