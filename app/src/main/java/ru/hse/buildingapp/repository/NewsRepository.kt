package ru.hse.buildingapp.repository

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.hse.buildingapp.network.RetrofitClient
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.NewsModel
import java.io.IOException

object NewsRepository {
    var news : RespState<MutableMap<Int, NewsModel>> = RespState.Loading()
        private set

    private val mutex = Mutex()

    suspend fun updateData() {
        mutex.withLock {
            try {
                news = RespState.Loading()
                val resp = RetrofitClient.retrofitService.getNews(0, 10)
                if (resp.isSuccessful) {

                    val updatedNews: RespState.Success<MutableMap<Int, NewsModel>> =
                        RespState.Success(mutableMapOf())
                    for (n in resp.body()!!)
                        updatedNews.res[n.id] = n
                    news = updatedNews
                } else
                    news = RespState.UnknownError(resp.code())

            } catch (e: IOException) {
                news = RespState.ConnectionError()
            }
        }
    }
}