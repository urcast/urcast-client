package com.example.urcast


data class WeatherNode(
    val name: String,
    val temperature: Double,
    val humidity: Int,
    val pressure: Int,
    val lightLevel: String,
    val lastRecorded: String,
    val iconFileName: String,
    val latitude: Double,
    val longitude: Double
)

val sampleNodes = listOf(
    WeatherNode(
        name = "Dundalk Node",
        temperature = 14.0,
        humidity = 72,
        pressure = 1013,
        lightLevel = "Available",
        lastRecorded = "10:20 AM",
        iconFileName = "clear-day.svg",
        latitude = 54.0000,
        longitude = -6.4050
    ),
    WeatherNode(
        name = "Drogheda Node",
        temperature = 16.2,
        humidity = 65,
        pressure = 1015,
        lightLevel = "High",
        lastRecorded = "10:15 AM",
        iconFileName = "haze.svg",
        latitude = 53.7179,
        longitude = -6.3561
    ),
    WeatherNode(
        name = "Dublin Node",
        temperature = 13.8,
        humidity = 80,
        pressure = 1009,
        lightLevel = "Low",
        lastRecorded = "10:22 AM",
        iconFileName = "rainy-1.svg",
        latitude = 53.3498,
        longitude = -6.2603
    )
)
