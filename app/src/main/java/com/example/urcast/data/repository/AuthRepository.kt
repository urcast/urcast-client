package com.example.urcast.data.repository

import com.example.urcast.data.remote.RetrofitClient
import com.example.urcast.data.remote.dto.LoginRequest
import com.example.urcast.data.remote.dto.TokenResponse
import com.example.urcast.data.remote.dto.RegisterRequest
import com.example.urcast.data.remote.dto.RegisterResponse

class AuthRepository(
    private val api: com.example.urcast.data.remote.UrCastApiService =
        RetrofitClient.api
) {

    suspend fun login(email: String, password: String): TokenResponse {
        return api.login(
            LoginRequest(
                email = email,
                password = password
            )
        )
    }
    suspend fun register(
        username: String,
        email: String,
        password: String
    ): RegisterResponse {
        return api.register(
            RegisterRequest(
                username = username,
                email = email,
                password = password
            )
        )
    }
}