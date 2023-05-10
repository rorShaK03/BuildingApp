package ru.hse.buildingapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ReportModel
import ru.hse.buildingapp.repository.ReportsRepository

class ReportsViewModelFactory(private val projectId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ReportsViewModel(projectId) as T
}

class ReportsViewModel(val projectId : Int) : ViewModel() {

    var reports : RespState<MutableMap<Int, ReportModel>> by mutableStateOf(RespState.Loading<MutableMap<Int, ReportModel>>())
    var isRefreshing by mutableStateOf(false)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            reports = RespState.Loading()
            ReportsRepository.updateData(projectId)
            reports = ReportsRepository.reports
        }
    }
}