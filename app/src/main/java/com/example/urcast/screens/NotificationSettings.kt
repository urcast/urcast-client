package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.urcast.NotificationPreferences

@Composable
fun NotificationSettings() {
    val context = LocalContext.current
    val preferences = remember {
        NotificationPreferences(context)
    }

    var rainAlerts by remember {
        mutableStateOf(preferences.isEnabled("rain_alerts", true))
    }
    var weatherWarnings by remember {
        mutableStateOf(preferences.isEnabled("weather_warnings", true))
    }
    var temperatureAlerts by remember {
        mutableStateOf(preferences.isEnabled("temperature_alerts", false))
    }
    var nodeOffline by remember {
        mutableStateOf(preferences.isEnabled("node_offline", true))
    }
    var sensorProblems by remember {
        mutableStateOf(preferences.isEnabled("sensor_problems", true))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Notification Settings",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Weather notifications",
            style = MaterialTheme.typography.titleMedium
        )

        NotificationSwitch(
            title = "Rain alerts",
            description = "Notify me when rain is expected.",
            checked = rainAlerts,
            onCheckedChange = {
                rainAlerts = it
                preferences.setEnabled("rain_alerts", it)
            }
        )

        NotificationSwitch(
            title = "Weather warnings",
            description = "Receive severe weather alerts.",
            checked = weatherWarnings,
            onCheckedChange = {
                weatherWarnings = it
                preferences.setEnabled("weather_warnings", it)
            }
        )

        NotificationSwitch(
            title = "Temperature alerts",
            description = "Notify me about unusual temperatures.",
            checked = temperatureAlerts,
            onCheckedChange = {
                temperatureAlerts = it
                preferences.setEnabled("temperature_alerts", it)
            }
        )

        HorizontalDivider()

        Text(
            text = "Contributor notifications",
            style = MaterialTheme.typography.titleMedium
        )

        NotificationSwitch(
            title = "Node offline",
            description = "When my weather station disconnects.",
            checked = nodeOffline,
            onCheckedChange = {
                nodeOffline = it
                preferences.setEnabled("node_offline", it)
            }
        )

        NotificationSwitch(
            title = "Sensor problems",
            description = "When a hardware component stops responding.",
            checked = sensorProblems,
            onCheckedChange = {
                sensorProblems = it
                preferences.setEnabled("sensor_problems", it)
            }
        )
    }
}

@Composable
fun NotificationSwitch(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}
