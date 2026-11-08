package com.example.urcast.data.remote

import com.example.urcast.data.remote.dto.NodeDto
import retrofit2.http.GET

interface UrCastApiService {

    @GET("api/nodes")
    suspend fun getNodes(): List<NodeDto>

}