package com.example.urcast
import android.content.Context

object WeatherAlertManager {

    // 1. Rain alert
    fun checkRainAlert(
        context: Context,
        rainExpectedWithinHour: Boolean
    ) {
        if (rainExpectedWithinHour) {
            NotificationHelper.showAlert(
                context = context,
                key = "rain_alerts",
                title = "Rain expected",
                message = "Rain is expected in your area within the next hour.",
                notificationId = 2001
            )
        }
    }

    // 2. Weather warning
    fun checkWeatherWarning(
        context: Context,
        warningMessage: String?
    ) {
        if (!warningMessage.isNullOrBlank()) {
            NotificationHelper.showAlert(
                context = context,
                key = "weather_warnings",
                title = "Weather warning",
                message = warningMessage,
                notificationId = 2002
            )
        }
    }

    // 3. Temperature alert
    fun checkTemperatureAlert(
        context: Context,
        temperature: Double,
        threshold: Double = 30.0
    ) {
        if (temperature > threshold) {
            NotificationHelper.showAlert(
                context = context,
                key = "temperature_alerts",
                title = "Temperature alert",
                message = "Temperature has reached $temperature°C.",
                notificationId = 2003,
                defaultValue = false
            )
        }
    }

    // 4. Weather node offline
    fun checkNodeOffline(
        context: Context,
        nodeName: String,
        minutesSinceLastUpdate: Int
    ) {
        if (minutesSinceLastUpdate >= 10) {
            NotificationHelper.showAlert(
                context = context,
                key = "node_offline",
                title = "Weather node offline",
                message = "$nodeName has not reported for $minutesSinceLastUpdate minutes.",
                notificationId = 2004
            )
        }
    }

    // 5. Sensor problem
    fun checkSensorProblem(
        context: Context,
        sensorName: String,
        errorMessage: String?
    ) {
        if (!errorMessage.isNullOrBlank()) {
            NotificationHelper.showAlert(
                context = context,
                key = "sensor_problems",
                title = "Sensor problem",
                message = "$sensorName: $errorMessage",
                notificationId = 2005
            )
        }
    }
}
