package com.example.urcast.data.repository

import com.example.urcast.data.remote.RetrofitClient
import com.example.urcast.data.remote.UrCastApiService
import com.example.urcast.data.remote.dto.NodeDto
import com.example.urcast.data.remote.dto.ReadingDto
import com.example.urcast.data.remote.dto.CreateNodeRequest

class NodeRepository(
    private val api: UrCastApiService = RetrofitClient.api
) {
    suspend fun getNodes(): List<NodeDto> {
        return api.getNodes()
    }

    suspend fun getReadings(): List<ReadingDto> {
        return api.getReadings()
    }

    suspend fun createNode(request: CreateNodeRequest): NodeDto {
        return api.createNode(request)
    }
}