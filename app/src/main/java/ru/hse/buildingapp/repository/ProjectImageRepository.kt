package ru.hse.buildingapp.repository

import android.graphics.Bitmap
import kotlinx.coroutines.sync.Mutex
import ru.hse.buildingapp.network.authmodels.RespState

object ProjectImageRepository {
    var img : RespState<Bitmap> = RespState.Loading()

    private val mutex = Mutex()

    suspend fun updateData() {
        /*
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
         */
        img = RespState.UnknownError(-1)
    }
}