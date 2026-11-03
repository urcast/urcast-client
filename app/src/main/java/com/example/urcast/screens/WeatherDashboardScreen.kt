package com.example.urcast.screens

import com.example.urcast.components.ReadingTile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.urcast.WeatherNode
import com.example.urcast.components.getWeatherIcon

@Composable
fun WeatherDashboardScreen(
    node: WeatherNode,
    temperatureUnit: String
) {
    // Converts Celsius to Fahrenheit when selected.
    val displayedTemperature = if (temperatureUnit == "°F") {
        (node.temperature * 9 / 5) + 32
    } else {
        node.temperature
    }

    val temperatureText = "${displayedTemperature.toInt()}$temperatureUnit"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- 1. HERO / LOCAL WEATHER SECTION ---
        Text(
            text = "YOUR LOCAL WEATHER",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(
                    id = getWeatherIcon(node.iconFileName)
                ),
                contentDescription = "${node.iconFileName} weather icon",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = temperatureText,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Latest temperature reading",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = node.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Schedule,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Last recorded: ${node.lastRecorded}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        WeatherOverview(temperatureUnit = temperatureUnit)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(20.dp))

        // --- 2. CURRENT READINGS GRID ---
        Text(
            text = "Current readings",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Row 1: Temperature & Humidity
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Thermostat,
                label = "Temperature",
                value = temperatureText,
                lightContainerColor = Color(0xFFFFEBEE),
                darkContainerColor = Color(0xFF2C1E1A),
                iconTint = Color(0xFFE53935)
            )
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.WaterDrop,
                label = "Humidity",
                value = "${node.humidity}%",
                lightContainerColor = Color(0xFFE3F2FD),
                darkContainerColor = Color(0xFF1A2332),
                iconTint = Color(0xFF1E88E5)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Row 2: Pressure & Light level
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.Speed,
                label = "Pressure",
                value = "${node.pressure} hPa",
                lightContainerColor = Color(0xFFF3E5F5),
                darkContainerColor = Color(0xFF221A30),
                iconTint = Color(0xFF8E24AA)
            )
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.WbSunny,
                label = "Light level",
                value = "${node.lightLevel} lux",
                lightContainerColor = Color(0xFFFFFDE7),
                darkContainerColor = Color(0xFF2E2A1A),
                iconTint = Color(0xFFFBC02D)
            )
        }
    }
}