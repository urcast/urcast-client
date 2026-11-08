package com.example.urcast.data.remote

import com.example.urcast.data.remote.dto.NodeDto
import retrofit2.http.GET
import com.example.urcast.data.remote.dto.LoginRequest
import com.example.urcast.data.remote.dto.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.urcast.data.remote.dto.RegisterRequest
import com.example.urcast.data.remote.dto.RegisterResponse

interface UrCastApiService {

    @GET("api/nodes")
    suspend fun getNodes(): List<NodeDto>

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): TokenResponse

    @POST("api/user")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse
}