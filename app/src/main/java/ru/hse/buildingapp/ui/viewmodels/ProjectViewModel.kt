package ru.hse.buildingapp.ui.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.repository.ProjectImageRepository
import ru.hse.buildingapp.repository.ProjectsRepository

class ProjectViewModelFactory(private val projectId : Int, private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProjectViewModel(projectId, navController) as T
}


class ProjectViewModel(val projectId : Int, val navController: NavHostController) : ViewModel() {

    var project : ProjectModel by mutableStateOf(ProjectModel(0, "Unknown", 0, 0, "Unknown", "Unknown"))
    var projectImg : Bitmap? by mutableStateOf(null)

    var isRefreshing by mutableStateOf(false)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            ProjectImageRepository.updateData()
            val imgState = ProjectImageRepository.img
            if(imgState is RespState.Success)
                projectImg = imgState.res
            else
                projectImg = null

            val projState = ProjectsRepository.projects
            project = if (projState is RespState.Success && projState.res[projectId] != null)
                projState.res[projectId]!!
            else
                ProjectModel(0, "Unknown", 0, 0, "Unknown", "Unknown")
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