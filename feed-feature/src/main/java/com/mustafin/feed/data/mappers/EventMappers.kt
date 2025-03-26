package com.mustafin.feed.data.mappers

import com.mustafin.feed.data.source.local.eventsSource.EventsEntity
import com.mustafin.feed.utils.events.EventModel

/* Мапперы для моделей данных событий */

fun EventsEntity.mapToEventModel(): EventModel {
    return EventModel(
        id = id,
        images = images,
        text = text,
        tags = tags.toSet(),
        linkedNews = linkedNews
    )
}

fun EventModel.mapToEventsEntity(): EventsEntity {
    return EventsEntity(
        id = id,
        images = images,
        text = text,
        tags = tags.toList(),
        linkedNews = linkedNews
    )
}