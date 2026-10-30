package com.example.urcast.screens

import com.example.urcast.components.ReadingTile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

@Composable
fun WeatherDashboardScreen(node: WeatherNode) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                painter = painterResource(id = node.iconResId),
                contentDescription = "Weather condition icon",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${node.temperature.toInt()}°C",
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
        ForecastHistoryTiles(
            onPeriodSelected = { index ->
                when (index) {
                    0 -> { /* 7 days history */ }
                    1 -> { /* 7 hours history */ }
                    2 -> { /* 7 hours forecast */ }
                    3 -> { /* 7 days forecast */ }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(20.dp))

        // --- 2. CURRENT READINGS GRID (2x2 TILES WITH ADAPTIVE COLORS) ---
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
                value = "${node.temperature.toInt()}°C",
                lightContainerColor = Color(0xFFFFEBEE), // Soft Light Red
                darkContainerColor = Color(0xFF2C1E1A),  // Soft Dark Red
                iconTint = Color(0xFFE53935)               // Vibrant Red/Orange
            )
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.WaterDrop,
                label = "Humidity",
                value = "${node.humidity}%",
                lightContainerColor = Color(0xFFE3F2FD), // Soft Light Blue
                darkContainerColor = Color(0xFF1A2332),  // Soft Dark Blue
                iconTint = Color(0xFF1E88E5)               // Vibrant Blue
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
                lightContainerColor = Color(0xFFF3E5F5), // Soft Light Purple
                darkContainerColor = Color(0xFF221A30),  // Soft Dark Purple
                iconTint = Color(0xFF8E24AA)               // Vibrant Purple
            )
            ReadingTile(
                modifier = Modifier.weight(1f),
                icon = Icons.Filled.WbSunny,
                label = "Light level",
                value = node.lightLevel,
                lightContainerColor = Color(0xFFFFFDE7), // Soft Light Amber
                darkContainerColor = Color(0xFF2E2A1A),  // Soft Dark Amber
                iconTint = Color(0xFFFBC02D)               // Vibrant Amber/Yellow
            )
        }
    }
}