package com.mustafin.feed.data.repositories.favoritesRepository

import com.mustafin.feed.utils.events.EventModel

interface FavoritesRepository {
    suspend fun addToFavorites(id: Int)
    suspend fun removeFromFavorites(id: Int)
    suspend fun getAllFavoritesIds(): List<Int>
    suspend fun getAllFavorites(): List<EventModel>
}