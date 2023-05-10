package ru.hse.buildingapp.ui.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.NewsModel
import ru.hse.buildingapp.repository.NewsRepository

class NewsViewModel : ViewModel() {


    var newsUiState : RespState<MutableMap<Int, NewsModel>> by mutableStateOf(RespState.Loading())
        private set

    var isRefreshing by mutableStateOf(false)

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            newsUiState = RespState.Loading()
            NewsRepository.updateData()
            newsUiState = NewsRepository.news
        }
    }

}