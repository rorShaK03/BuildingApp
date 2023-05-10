package ru.hse.buildingapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectRequestModel
import java.io.IOException

class ProjectRequestViewModelFactory(private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ProjectRequestViewModel(navController) as T
}

class ProjectRequestViewModel(val navController: NavHostController) : ViewModel() {

    var state : RespState<Unit> by mutableStateOf(RespState.Loading())

    fun submit(name: String, email: String, phone: String, projName : String,
               projType : String, totalArea: String, budget: String, city: String) {

        viewModelScope.launch {

            try {
                createRequest(
                    ProjectRequestModel(
                        name, email, phone,
                        projName, projType, totalArea.toInt(),
                        budget.toInt(), city
                    )
                )
            } catch (e: Exception) {
                state = RespState.UnknownError(-1)
            }
        }

    }


    private suspend fun createRequest(p : ProjectRequestModel) {
        try {
            val resp = RetrofitClient.retrofitService.projectRequest(p)
            if (resp.isSuccessful)
                state = RespState.Success(Unit)
            else
                state = RespState.UnknownError(resp.code())
        } catch(e : IOException) {
            state = RespState.ConnectionError()
        }
    }


}