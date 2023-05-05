package ru.hse.buildingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.network.models.ProjectStatus
import ru.hse.buildingapp.repository.ProjectsRepository

class ProjectsListViewModel : ViewModel() {

        var projects : List<ProjectModel> = listOf()
        var preparingFraction : Float = 0.0f
        var onProgressFraction : Float = 0.0f
        var doneFraction : Float = 0.0f

        init {
            viewModelScope.launch {
                ProjectsRepository.updateData()
                projects = ProjectsRepository.projects.values.toList()
                updateFractions()
            }
        }

        private fun updateFractions() {
            val preparingCnt : Int = projects.count { it.status == ProjectStatus.PREPARING }
            val onProgressCnt : Int = projects.count { it.status == ProjectStatus.ON_PROGRESS }
            val doneCnt : Int = projects.count { it.status == ProjectStatus.DONE }

            if(projects.isNotEmpty()) {
                preparingFraction = preparingCnt.toFloat() / projects.size
                onProgressFraction = onProgressCnt.toFloat() / projects.size
                doneFraction = doneCnt.toFloat() / projects.size
            }
        }

}