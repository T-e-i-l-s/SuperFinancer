package com.mustafin.feed.presentation.screens.feedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.feed.data.repositories.eventsRepository.EventsRepository
import com.mustafin.feed.data.repositories.favoritesRepository.FavoritesRepository
import com.mustafin.feed.utils.events.EventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* ViewModel экрана ленты */
@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val eventsRepository: EventsRepository,
    private val favoritesRepository: FavoritesRepository
): ViewModel() {
    private val _events = MutableStateFlow<List<EventModel>?>(null)
    val events: StateFlow<List<EventModel>?> = _events

    private val _favoritesIds = MutableStateFlow<List<Int>>(emptyList())
    val favoritesIds: StateFlow<List<Int>> = _favoritesIds

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _events.value = eventsRepository.getAllEvents()
            _favoritesIds.value = favoritesRepository.getAllFavoritesIds()
        }
    }

    fun toggleIsFavorite(id: Int) {
        viewModelScope.launch {
            if (favoritesIds.value.contains(id)) {
                favoritesRepository.removeFromFavorites(id)
            } else {
                favoritesRepository.addToFavorites(id)
            }
            _favoritesIds.value = favoritesRepository.getAllFavoritesIds()
        }
    }
}