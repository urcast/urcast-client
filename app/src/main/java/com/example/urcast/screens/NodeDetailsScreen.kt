
package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urcast.WeatherNode

@Composable
fun NodeDetailsScreen(
    node: WeatherNode,
    onBack: () -> Unit,
    temperatureUnit: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Back button
        TextButton(onClick = onBack) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
            Spacer(Modifier.width(8.dp))
            Text("Back to Profile")
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = node.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Hardware diagnostics and node health",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(20.dp))

        // Hardware components
        Text(
            text = "HARDWARE COMPONENTS",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(12.dp))

        if (node.hardwareComponents.isEmpty()) {
            Text(
                "No hardware diagnostic information available yet."
            )
        } else {
            node.hardwareComponents.forEach { component ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Memory,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                component.name,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                component.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(Modifier.height(6.dp))

                            Text(
                                text = "Status: ${component.status}",
                                style = MaterialTheme.typography.labelMedium,
                                color = when (component.status.lowercase()) {
                                    "online", "connected", "working" ->
                                        MaterialTheme.colorScheme.primary

                                    "offline", "disconnected", "error" ->
                                        MaterialTheme.colorScheme.error

                                    else ->
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                }
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Current readings
        Text(
            text = "LATEST SENSOR READINGS",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(12.dp))

        val displayedTemperature = if (temperatureUnit == "°F") {
            node.temperature * 9 / 5 + 32
        } else {
            node.temperature
        }

        Text("Temperature: ${displayedTemperature.toInt()}$temperatureUnit")
        Text("Humidity: ${node.humidity}%")
        Text("Pressure: ${node.pressure} hPa")
        Text("Light level: ${node.lightLevel} lux")

        Spacer(Modifier.height(12.dp))

        Text(
            text = "Last recorded: ${node.lastRecorded}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
