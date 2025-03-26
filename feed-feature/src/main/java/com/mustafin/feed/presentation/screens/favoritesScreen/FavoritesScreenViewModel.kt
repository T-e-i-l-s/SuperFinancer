package com.mustafin.feed.presentation.screens.favoritesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.feed.data.repositories.favoritesRepository.FavoritesRepository
import com.mustafin.feed.utils.events.EventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* ViewModel экрана со списом избранных событий */
@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
): ViewModel() {
    private val _favoriteEvents = MutableStateFlow<List<EventModel>?>(null)
    val favoriteEvents: StateFlow<List<EventModel>?> = _favoriteEvents

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _favoriteEvents.value = favoritesRepository.getAllFavorites()
        }
    }
}