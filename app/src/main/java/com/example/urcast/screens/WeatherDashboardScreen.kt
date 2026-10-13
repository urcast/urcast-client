
package com.example.urcast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDashboardScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Welcome message
        Text("Welcome to urcast!")
        Text("Your local weather dashboard")

        Spacer(modifier = Modifier.height(24.dp))

        // Page title
        Text("Local Weather")

        Spacer(modifier = Modifier.height(24.dp))

        // Main temperature
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("-- °C")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Current Temperature")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Humidity and light readings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Humidity")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("-- %")
                }
            }

            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Light")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("-- lux")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Location
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Location")
                Spacer(modifier = Modifier.height(8.dp))
                Text("No location data available")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Last updated
        Text("Last updated: --")
    }
}
