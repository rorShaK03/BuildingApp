package ru.hse.buildingapp.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.MeetingModel
import java.io.IOException

object MeetingsRepository {
    var meetings : RespState<List<MeetingModel>> = RespState.Loading()
        private set

    private val mutex = Mutex()

    suspend fun updateData() {
        mutex.withLock {
            try {
                meetings = RespState.Loading()
                val resp = RetrofitClient.retrofitService.getMeetings()
                if (resp.isSuccessful) {
                    meetings = RespState.Success(resp.body()!!)
                } else
                    meetings = RespState.UnknownError(resp.code())

            } catch (e: IOException) {
                meetings = RespState.ConnectionError()
            }
        }
    }
}