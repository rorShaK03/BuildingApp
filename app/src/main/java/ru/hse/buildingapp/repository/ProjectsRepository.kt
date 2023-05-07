package ru.hse.buildingapp.repository

import ru.hse.buildingapp.network.BackendApi
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectModel
import java.io.IOException

object ProjectsRepository {
    var projects : RespState<MutableMap<Int, ProjectModel>> = RespState.Loading()
        private set


    suspend fun updateData() {
        try {
            val resp = BackendApi.retrofitService.getProjects()
            if(resp.isSuccessful) {
                val updatedProjects : RespState.Success<MutableMap<Int, ProjectModel>> = RespState.Success(mutableMapOf())
                for (n in resp.body()!!)
                    updatedProjects.res[n.id] = n
                projects = updatedProjects
            }
            else
                projects = RespState.UnknownError(resp.code())

        }
        catch(e : IOException) {
            projects = RespState.ConnectionError()
        }
    }
}