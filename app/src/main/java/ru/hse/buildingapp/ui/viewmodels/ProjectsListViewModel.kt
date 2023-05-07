package ru.hse.buildingapp.ui.viewmodels

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
import ru.hse.buildingapp.network.models.ProjectStatus
import ru.hse.buildingapp.repository.ProjectsRepository

class ProjectsListViewModelFactory(private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProjectsListViewModel(navController) as T
}

class ProjectsListViewModel(val navController: NavHostController) : ViewModel() {


        var projects : RespState<MutableMap<Int, ProjectModel>> by mutableStateOf(RespState.Loading())
        var preparingFraction : Float by mutableStateOf(0.0f)
        var onProgressFraction : Float by mutableStateOf(0.0f)
        var doneFraction : Float by mutableStateOf(0.0f)

        init {
            viewModelScope.launch {
                ProjectsRepository.updateData()
                projects = ProjectsRepository.projects
                updateFractions()
            }
        }

        private fun updateFractions() {
            val state = projects
            if(state is RespState.Success) {
                val preparingCnt: Int = state.res.values.count { it.projStatus == ProjectStatus.PREPARING }
                val onProgressCnt: Int =
                    state.res.values.count { it.projStatus == ProjectStatus.ON_PROGRESS }
                val doneCnt: Int = state.res.values.count { it.projStatus == ProjectStatus.DONE }

                if (state.res.values.isNotEmpty()) {
                    preparingFraction = preparingCnt.toFloat() / state.res.values.size
                    onProgressFraction = onProgressCnt.toFloat() / state.res.values.size
                    doneFraction = doneCnt.toFloat() / state.res.values.size
                }
            }
        }

}