package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDashboardScreen(node: WeatherNode) { // Add parameter here

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // App heading
        Text("urcast")
        Text("Local weather, from your community")

        Spacer(modifier = Modifier.height(24.dp))

        // Main temperature card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("YOUR LOCAL WEATHER")
                Text("${node.temperature.toInt()}°C")
                Text("Latest temperature reading")
                Text("Location: ${node.name}")
                Text("Last recorded: ${node.lastRecorded}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Current readings")

        Spacer(modifier = Modifier.height(8.dp))

        // Temperature card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Temperature")
                Text("${node.temperature.toInt()}°C")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Humidity card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Humidity")
                Text("${node.humidity}%")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pressure card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Pressure")
                Text("${node.pressure} hPa")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Light level card
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Light level")
                Text(node.lightLevel)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Map section
        Text("Nearby weather nodes")
        Text("Explore available local sensors and select a location.")

        Spacer(modifier = Modifier.height(24.dp))

        // History section
        Text("Weather trends")
        Text("View recent changes in temperature, humidity and pressure.")
    }
}