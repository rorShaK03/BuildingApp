package ru.hse.buildingapp.repository

import ru.hse.buildingapp.network.BackendApi
import ru.hse.buildingapp.network.models.NewsModel
import java.io.IOException

object NewsRepository {
    var news : MutableMap<Int, NewsModel> = mutableMapOf()
        private set

    suspend fun updateData() {
        val newsList : List<NewsModel> = try {
            val listResults = BackendApi.retrofitService.getNews(0, 1)
            listResults
        }
        catch(e : IOException) {
            throw e
        }
        for(n in newsList)
            news[n.id] = n
    }
}