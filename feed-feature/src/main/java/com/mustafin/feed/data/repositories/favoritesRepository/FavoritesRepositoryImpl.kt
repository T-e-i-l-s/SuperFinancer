package com.mustafin.feed.data.repositories.favoritesRepository

import com.mustafin.feed.data.mappers.mapToEventModel
import com.mustafin.feed.data.source.local.eventsSource.EventsDao
import com.mustafin.feed.data.source.local.favoritesSource.FavoritesSource
import com.mustafin.feed.utils.events.EventModel
import javax.inject.Inject

/* Репозиторий для работы с избранными событиями */
class FavoritesRepositoryImpl @Inject constructor(
    private val eventsDao: EventsDao,
    private val favoritesSource: FavoritesSource
): FavoritesRepository {
    override suspend fun addToFavorites(id: Int) {
        val currentFavorites = favoritesSource.getFavoriteIds().toMutableList()
        if (!currentFavorites.contains(id)) {
            currentFavorites.add(id)
            favoritesSource.saveFavoriteIds(currentFavorites)
        }
    }

    override suspend fun removeFromFavorites(id: Int) {
        val currentFavorites = favoritesSource.getFavoriteIds().toMutableList()
        if (currentFavorites.remove(id)) {
            favoritesSource.saveFavoriteIds(currentFavorites)
        }
    }

    override suspend fun getAllFavoritesIds(): List<Int> {
        return favoritesSource.getFavoriteIds()
    }

    override suspend fun getAllFavorites(): List<EventModel> {
        val favoriteIds = favoritesSource.getFavoriteIds()
        return eventsDao.getEventsByIds(favoriteIds).map { it.mapToEventModel() }
    }
}