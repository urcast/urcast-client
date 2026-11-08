package com.example.urcast.data.remote

import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://urcast.pjmisev.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->

            val originalRequest = chain.request()

            val token = TokenManager.accessToken

            val newRequest = if (token != null) {
                originalRequest.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else {
                originalRequest
            }

            chain.proceed(newRequest)
        }
        .build()

    val api: UrCastApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UrCastApiService::class.java)
    }
}