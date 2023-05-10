package ru.hse.buildingapp.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.ReportModel
import java.io.IOException

object ReportsRepository {
    var reports : RespState<MutableMap<Int, ReportModel>> = RespState.Loading()
        private set

    private val mutex = Mutex()


    suspend fun updateData(projectId : Int) {
        mutex.withLock {
            try {
                reports = RespState.Loading()
                val resp = RetrofitClient.retrofitService.getReports(projectId)
                if (resp.isSuccessful) {
                    val updatedReports: RespState.Success<MutableMap<Int, ReportModel>> =
                        RespState.Success(mutableMapOf())
                    for (n in resp.body()!!)
                        updatedReports.res[n.id] = n
                    reports = updatedReports
                } else
                    reports = RespState.UnknownError(resp.code())

            } catch (e: IOException) {
                reports = RespState.ConnectionError()
            }
        }
    }
}