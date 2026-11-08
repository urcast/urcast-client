package com.example.urcast.data.repository

import com.example.urcast.data.remote.RetrofitClient
import com.example.urcast.data.remote.dto.NodeDto

class NodeRepository(
    private val api: com.example.urcast.data.remote.UrCastApiService =
        RetrofitClient.api
) {

    suspend fun getNodes(): List<NodeDto> {
        return api.getNodes()
    }
}