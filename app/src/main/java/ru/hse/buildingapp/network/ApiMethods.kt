package ru.hse.buildingapp.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*
import ru.hse.buildingapp.network.authmodels.AuthRespState
import ru.hse.buildingapp.network.authmodels.Credentials
import ru.hse.buildingapp.network.authmodels.RefreshTokensAdapter
import ru.hse.buildingapp.network.authmodels.Tokens
import ru.hse.buildingapp.network.models.NewsModel
import ru.hse.buildingapp.network.models.ProjectModel
import ru.hse.buildingapp.network.models.ProjectRequestModel
import ru.hse.buildingapp.network.models.ReportModel
import java.io.IOException


interface ApiMethods {


    @GET("api/News")
    suspend fun getNews(@Query("skip") skip : Int, @Query("take") take : Int) : Response<List<NewsModel>>

    @GET("api/Project")
    suspend fun getProjects() : Response<List<ProjectModel>>
    @GET("api/Reports/{id}")
    suspend fun getReports(@Path("id") projId : Int) : Response<List<ReportModel>>
    @GET("api/ImageProject/{id}")
    suspend fun getImage(@Path("id") imgId : Int) : Response<ResponseBody>
    @POST("api/Proposal")
    suspend fun projectRequest(@Body p : ProjectRequestModel) : Response<Unit>
    @POST("accounts/login")
    suspend fun login(@Body c : Credentials) : Response<Tokens>
    @POST("accounts/refresh-token")
    suspend fun refreshToken(@Body t : RefreshTokensAdapter) : RefreshTokensAdapter

}

object RetrofitClient {
    private const val BASE_URL = "https://10.0.2.2:5001"
    private val json = Json { ignoreUnknownKeys = true }

    var tokens : Tokens = Tokens()


    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(BASE_URL)
        .client(UnsafeHttpClient.createOkHttpClient()!!)
        .build()

    val retrofitService : ApiMethods by lazy {
        retrofit.create(ApiMethods::class.java)
    }

    suspend fun authorize(login : String, password : String) : AuthRespState {
        try {
            val resp = retrofitService.login(Credentials(login, password))
            if (resp.isSuccessful) {
                tokens = resp.body()!!
                return AuthRespState.Success
            } else if (resp.code() == 400)
                return AuthRespState.InvalidCredentials
            else
                return AuthRespState.UnknownServerError(resp.code())
        }
        catch(e : IOException) {
            return AuthRespState.ConnectionError
        }
    }

    suspend fun refreshToken() {
        val newTokensAdapter : RefreshTokensAdapter = retrofitService.refreshToken(
            RefreshTokensAdapter(
                tokens.token, tokens.refreshToken)
        )
        tokens.token = newTokensAdapter.token
        tokens.refreshToken = newTokensAdapter.refreshToken
    }
}


