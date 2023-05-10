package ru.hse.buildingapp.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import java.io.IOException

object ProjectImageRepository {
    var img : RespState<Bitmap> = RespState.Loading()

    private val mutex = Mutex()

    suspend fun updateData() {
        mutex.withLock {
            try {
                img = RespState.Loading()
                val resp = RetrofitClient.retrofitService.getImage(2)
                if(resp.isSuccessful && resp.body() != null) {
                    img = RespState.Success(BitmapFactory.decodeStream(resp.body()!!.byteStream()))
                }
                else
                    img = RespState.UnknownError(resp.code())
            }
            catch(e : IOException) {
                img = RespState.ConnectionError()
            }
        }
    }
}