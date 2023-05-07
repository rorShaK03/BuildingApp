package ru.hse.buildingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.repository.ProjectsRepository

class ReportsViewModelFactory(private val projectId: Int) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ReportsViewModel(projectId) as T
}

class ReportsViewModel(projectId : Int) : ViewModel() {

    var project : ProjectModel

    init {
        val state = ProjectsRepository.projects
        project = if (state is RespState.Success && state.res[projectId] != null)
            state.res[projectId]!!
        else
            ProjectModel(0, "Unknown", 0, 0, "Unknown", "Unknown")
    }
}