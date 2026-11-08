package com.example.urcast.data.remote.dto

data class CreateNodeRequest(
    val node_name: String,
    val latitude: Double,
    val longitude: Double,
    val location_name: String
)