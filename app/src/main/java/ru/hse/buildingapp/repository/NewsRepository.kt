package ru.hse.buildingapp.repository

import ru.hse.buildingapp.network.BackendApi
import ru.hse.buildingapp.network.authmodels.RespState
import ru.hse.buildingapp.network.models.NewsModel
import java.io.IOException

object NewsRepository {
    var news : RespState<MutableMap<Int, NewsModel>> = RespState.Loading()
        private set

    suspend fun updateData() {
        try {
            val resp = BackendApi.retrofitService.getNews(0, 10)
            if(resp.isSuccessful) {

                val updatedNews : RespState.Success<MutableMap<Int, NewsModel>> = RespState.Success(mutableMapOf())
                for (n in resp.body()!!)
                    updatedNews.res[n.id] = n
                news = updatedNews
            }
            else
                news = RespState.UnknownError(resp.code())

        }
        catch(e : IOException) {
            news = RespState.ConnectionError()
        }
    }
}