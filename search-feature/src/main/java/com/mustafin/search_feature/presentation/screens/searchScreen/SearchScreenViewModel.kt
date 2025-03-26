package com.mustafin.search_feature.presentation.screens.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.search_feature.data.repositories.SearchRepository
import com.mustafin.search_feature.utils.SearchState
import com.mustafin.search_feature.utils.search.AISearchResultModel
import com.mustafin.search_feature.utils.search.SearchResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* ViewModel основного экрана поиска */
@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _searchState = MutableStateFlow(SearchState.NO_QUERY)
    val searchState: StateFlow<SearchState> = _searchState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _aiSearchState = MutableStateFlow(SearchState.NO_QUERY)
    val aiSearchState: StateFlow<SearchState> = _aiSearchState

    private val _aiSearchResults = MutableStateFlow<AISearchResultModel?>(null)
    val aiSearchResults: StateFlow<AISearchResultModel?> = _aiSearchResults

    private val _searchResults = MutableStateFlow<SearchResultModel?>(null)
    val searchResults: StateFlow<SearchResultModel?> = _searchResults

    fun loadNewData() {
        _searchState.value = SearchState.SEARCHING
        _aiSearchState.value = SearchState.SEARCHING
        search()
    }

    fun updateData() {
        _searchState.value = SearchState.UPDATING
        _aiSearchState.value = SearchState.UPDATING
        search()
    }

    private fun search() {
        viewModelScope.launch {
            _searchResults.value = searchRepository.search(searchQuery.value)
            _searchState.value = SearchState.COMPLETED

            _aiSearchResults.value = searchRepository.askAI(searchQuery.value).getOrNull()
            _aiSearchState.value = SearchState.COMPLETED
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) _searchState.value = SearchState.NO_QUERY
    }
}