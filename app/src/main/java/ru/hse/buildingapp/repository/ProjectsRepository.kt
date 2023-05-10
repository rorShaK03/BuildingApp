package ru.hse.buildingapp.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ProjectModel
import java.io.IOException

object ProjectsRepository {
    var projects : RespState<MutableMap<Int, ProjectModel>> = RespState.Loading()
        private set

    private val mutex = Mutex()


    suspend fun updateData() {
        mutex.withLock {
            try {
                projects = RespState.Loading()
                val resp = RetrofitClient.retrofitService.getProjects()
                if (resp.isSuccessful) {
                    val updatedProjects: RespState.Success<MutableMap<Int, ProjectModel>> =
                        RespState.Success(mutableMapOf())
                    for (n in resp.body()!!)
                        updatedProjects.res[n.id] = n
                    projects = updatedProjects
                } else
                    projects = RespState.UnknownError(resp.code())

            } catch (e: IOException) {
                projects = RespState.ConnectionError()
            }
        }
    }
}