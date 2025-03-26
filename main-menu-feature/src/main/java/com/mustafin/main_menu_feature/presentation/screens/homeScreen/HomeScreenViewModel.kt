package com.mustafin.main_menu_feature.presentation.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.core.utils.LoadingState
import com.mustafin.core.utils.news.NewsModel
import com.mustafin.core.utils.tickers.TickerModel
import com.mustafin.main_menu_feature.data.repository.newsRepository.NewsRepository
import com.mustafin.main_menu_feature.data.repository.tickersRepository.TickersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* ViewModel главного экрана приложения */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val tickersRepository: TickersRepository
) : ViewModel() {
    private val _tickersLoadingState = MutableStateFlow(LoadingState.LOADING)
    val tickersLoadingState: StateFlow<LoadingState> = _tickersLoadingState

    private val _tickersList = MutableStateFlow<List<TickerModel>?>(null)
    val tickersList: StateFlow<List<TickerModel>?> = _tickersList

    private val _newsLoadingState = MutableStateFlow(LoadingState.LOADING)
    val newsLoadingState: StateFlow<LoadingState> = _newsLoadingState

    private val _news = MutableStateFlow<List<NewsModel>?>(null)
    val news: StateFlow<List<NewsModel>?> = _news


    init {
        loadAllData()
    }

    fun loadAllData() {
        loadTickets()
        loadNews()
    }

    fun loadTickets() {
        viewModelScope.launch {
            _tickersLoadingState.value = LoadingState.LOADING
            val ticketsResult = tickersRepository.getTickersInfo()
            if (ticketsResult.isSuccess) {
                _tickersList.value = ticketsResult.getOrNull()
                _tickersLoadingState.value = LoadingState.LOADED
            } else {
                _tickersLoadingState.value = LoadingState.ERROR
            }
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            _newsLoadingState.value =
                if (news.value == null) LoadingState.LOADING else LoadingState.UPDATING
            newsRepository.getAllNews(
                loadCachedNews = { news ->
                    _news.value = news
                    _newsLoadingState.value = LoadingState.UPDATING
                },
                loadCurrentNews = { news ->
                    _news.value = news
                    _newsLoadingState.value = LoadingState.LOADED
                },
                onLoadingError = {
                    if (_newsLoadingState.value != LoadingState.UPDATING) {
                        _newsLoadingState.value = LoadingState.ERROR
                    }
                }
            )
        }
    }
}