package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationSettings() {

    var rainAlerts by remember { mutableStateOf(true) }
    var weatherWarnings by remember { mutableStateOf(true) }
    var temperatureAlerts by remember { mutableStateOf(false) }
    var nodeOffline by remember { mutableStateOf(true) }
    var sensorProblems by remember { mutableStateOf(true) }

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
            onCheckedChange = { rainAlerts = it }
        )

        NotificationSwitch(
            title = "Weather warnings",
            description = "Receive severe weather alerts.",
            checked = weatherWarnings,
            onCheckedChange = { weatherWarnings = it }
        )

        NotificationSwitch(
            title = "Temperature alerts",
            description = "Notify me about unusual temperatures.",
            checked = temperatureAlerts,
            onCheckedChange = { temperatureAlerts = it }
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
            onCheckedChange = { nodeOffline = it }
        )

        NotificationSwitch(
            title = "Sensor problems",
            description = "When a hardware component stops responding.",
            checked = sensorProblems,
            onCheckedChange = { sensorProblems = it }
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
        Column(
            modifier = Modifier.weight(1f)
        ) {
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