package ru.hse.buildingapp.ui.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.models.NewsModel
import ru.hse.buildingapp.repository.NewsRepository
import java.io.IOException

class NewsViewModel : ViewModel() {

    /*
    sealed interface NewsUiState {
        data class Success(val news : List<NewsModel>) : NewsUiState
        object Loading : NewsUiState
        object Error : NewsUiState
    }
    */

    var newsUiState : List<NewsModel> by mutableStateOf(listOf())
        private set

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsUiState = try {
                NewsRepository.updateData()
                NewsRepository.news.values.toList()
            }
            catch(e : IOException) {
                throw e
            }
        }
    }

}