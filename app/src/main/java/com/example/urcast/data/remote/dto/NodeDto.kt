package com.example.urcast.data.remote.dto

data class NodeDto(
    val node_name: String,
    val latitude: Double?,
    val longitude: Double?,
    val location_name: String?,
    val node_id: Int,
    val user_id: Int,
    val status: String,
    val installed_at: String?,
    val last_seen: String?
)