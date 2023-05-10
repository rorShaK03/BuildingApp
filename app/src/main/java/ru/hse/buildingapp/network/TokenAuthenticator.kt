package ru.hse.buildingapp.network

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route : Route?, response : Response) : Request? {
        runBlocking {
            RetrofitClient.refreshToken()
        }
        return response.request()
            .newBuilder()
            .header("Authorization", "Bearer " + RetrofitClient.tokens.token)
            .build()
    }
}