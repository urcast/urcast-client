
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
fun WeatherDashboardScreen() {

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

        // Main temperature
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Local Weather")
                Text("-- °C")
                Text("Last recorded: --")
                Text("Location: Unavailable")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Humidity
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Humidity")
                Text("-- %")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pressure
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Pressure")
                Text("-- hPa")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Light level
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Light Level")
                Text("Unavailable")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Map section
        Text("Nearby Weather Nodes")
        Text("Explore weather nodes on the map.")

        Spacer(modifier = Modifier.height(24.dp))

        // History section
        Text("Weather History")
        Text("View temperature, humidity and pressure trends.")
    }
}
