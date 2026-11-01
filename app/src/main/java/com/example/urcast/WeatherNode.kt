package com.example.urcast

data class WeatherNode(
    val name: String,
    val temperature: Double,
    val humidity: Int,
    val pressure: Int,
    val lightLevel: Int,
    val lastRecorded: String,
    val iconFileName: String,
    val latitude: Double,
    val longitude: Double,
    val hardwareComponents: List<HardwareComponent> = emptyList()
)