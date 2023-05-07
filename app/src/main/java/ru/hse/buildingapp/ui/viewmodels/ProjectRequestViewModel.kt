package ru.hse.buildingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.BackendApi
import ru.hse.buildingapp.network.BackendService
import ru.hse.buildingapp.network.models.ProjectRequestModel

class ProjectRequestViewModel : ViewModel() {

    fun createRequest(p : ProjectRequestModel) {
        viewModelScope.launch {
            BackendApi.retrofitService.projectRequest(p)
        }
    }
}