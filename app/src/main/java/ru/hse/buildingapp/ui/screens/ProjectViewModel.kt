package ru.hse.buildingapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.repository.ProjectsRepository


class ProjectViewModel(projectId : Int) : ViewModel() {

    var project : ProjectModel by mutableStateOf(ProjectModel(0,
        "Unknown",
                    0,
                    0,
                    "Unknown"))

    init {
        viewModelScope.launch {
            if (ProjectsRepository.projects[projectId] != null)
                project = ProjectsRepository.projects[projectId]!!
        }
    }
    /*
    var dueDate : String by mutableStateOf("12/12/1999")
        private set
    var location : String by mutableStateOf("UAE-DUBAI")
        private set
    var status : String by mutableStateOf("On Progress")
        private set
    var createdBy : String by mutableStateOf("Infostrategic")
        private set
    var RFIManager : String by mutableStateOf("DUBAI")
        private set
    var assignees : String by mutableStateOf("Default name")
        private set
    var receivedFrom : String by mutableStateOf("Default name")
        private set
    var responsibleContractor : String by mutableStateOf("Default name")
        private set
    var drawingNumber : String by mutableStateOf("None")
        private set
    */

}