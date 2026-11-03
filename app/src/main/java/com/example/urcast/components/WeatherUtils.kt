package com.example.urcast.components


import com.example.urcast.R

fun getWeatherIcon(condition: String): Int {
    return when (condition.trim().lowercase()) {
        "clear_day" -> R.drawable.clear_day
        "clear_night" -> R.drawable.clear_night
        "cloudy" -> R.drawable.cloudy
        "cloudy_1_day" -> R.drawable.cloudy_1_day
        "cloudy_1_night" -> R.drawable.cloudy_1_night
        "day" -> R.drawable.day
        "dust" -> R.drawable.dust
        "fog" -> R.drawable.fog
        "frost" -> R.drawable.frost
        "haze_day" -> R.drawable.haze_day
        "haze_night" -> R.drawable.haze_night
        "rainy_1" -> R.drawable.rainy_1
        "rainy_1_day" -> R.drawable.rainy_1_day
        "rainy_1_night" -> R.drawable.rainy_1_night
        "rainy_3_day" -> R.drawable.rainy_3_day
        "rainy_3_night" -> R.drawable.rainy_3_night
        else -> R.drawable.windy
    }
}