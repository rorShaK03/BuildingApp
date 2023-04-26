package ru.hse.buildingapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.BackendApi
import java.io.IOException

class NewsViewModel : ViewModel() {

    sealed interface NewsUiState {
        data class Success(val news : String) : NewsUiState
        object Loading : NewsUiState
        object Error : NewsUiState
    }

    var newsUiState : NewsUiState by mutableStateOf(NewsUiState.Loading)
        private set

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsUiState = try {
                val listResults = BackendApi.retrofitService.getNews()
                NewsUiState.Success(listResults)
            }
            catch(_ : IOException) {
                NewsUiState.Error
            }
        }
    }

}