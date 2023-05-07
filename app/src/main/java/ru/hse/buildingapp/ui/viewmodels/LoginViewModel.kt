package ru.hse.buildingapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ru.hse.buildingapp.network.BackendApi
import ru.hse.buildingapp.network.authmodels.AuthRespState

class LoginViewModelFactory(private val navController: NavHostController) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginViewModel(navController) as T
}

class LoginViewModel(val navController: NavHostController) : ViewModel() {

    var state : AuthRespState by mutableStateOf(AuthRespState.Loading)

    fun submit(login : String, password : String) {
        state = AuthRespState.Loading
        viewModelScope.launch {
            state = BackendApi.authorize(login, password)
        }
    }
}