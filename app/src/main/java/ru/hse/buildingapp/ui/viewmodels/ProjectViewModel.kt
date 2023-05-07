package ru.hse.buildingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.repository.ProjectsRepository

class ProjectViewModelFactory(private val projectId : Int, private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProjectViewModel(projectId, navController) as T
}


class ProjectViewModel(projectId : Int, val navController: NavHostController) : ViewModel() {

    var project : ProjectModel

    init {
        val state = ProjectsRepository.projects
        project = if (state is RespState.Success && state.res[projectId] != null)
            state.res[projectId]!!
        else
            ProjectModel(0, "Unknown", 0, 0, "Unknown", "Unknown")
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