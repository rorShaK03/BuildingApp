package ru.hse.buildingapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ru.hse.buildingapp.network.models.NewsModel

private const val BASE_URL = "https://10.0.2.2:5001"
private val json = Json { ignoreUnknownKeys = true }

@OptIn(ExperimentalSerializationApi::class)
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .client(UnsafeHttpClient.createOkHttpClient()!!)
    .build()

interface BackendService {

    @GET("api/News")
    suspend fun getNews(@Query("skip") skip: Int, @Query("take") take: Int) : List<NewsModel>

}

object BackendApi {
    val retrofitService : BackendService by lazy {
        retrofit.create(BackendService::class.java)
    }
}


