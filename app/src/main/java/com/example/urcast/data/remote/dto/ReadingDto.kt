package com.example.urcast.data.remote.dto

data class ReadingDto(
    val reading_id: Int,
    val node_id: Int,
    val temperature: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val light_level: Double?,
    val recorded_at: String
)