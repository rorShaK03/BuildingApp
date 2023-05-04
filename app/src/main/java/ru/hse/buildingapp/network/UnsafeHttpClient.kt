package ru.hse.buildingapp.network

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


// ТОЛЬКО ДЛЯ ОТЛАДКИ!
// Генерирует HttpClient с отключенной проверкой SSL-сертификата
object UnsafeHttpClient {
        fun createOkHttpClient(): OkHttpClient? {
            return try {
                val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                    @SuppressLint("CustomX509TrustManager")
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )
                val sslContext: SSLContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                OkHttpClient.Builder()
                    .sslSocketFactory(
                        sslContext.getSocketFactory(),
                        trustAllCerts[0] as X509TrustManager
                    )
                    .hostnameVerifier { hostname: String?, session: SSLSession? -> true }
                    .build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
}