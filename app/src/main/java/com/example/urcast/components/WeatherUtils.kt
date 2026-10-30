package com.example.urcast.components


import com.example.urcast.R

fun getWeatherIcon(condition: String): Int {
    return when (condition.trim().lowercase()) {
        "windy" -> R.drawable.wind
        "cloudy" -> R.drawable.cloudy
        "cloudy day" -> R.drawable.cloudy_1_day
        "light rain" -> R.drawable.rainy_1
        "fog" -> R.drawable.fog
        else -> R.drawable.clear_day
    }
}