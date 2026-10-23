
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
fun NodeDetailsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Page heading
        Text("Node Details")
        Text("View information about this weather node.")

        Spacer(modifier = Modifier.height(24.dp))

        // Node information
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Node Name")
                Text("My Weather Node")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Node ID")
                Text("Unavailable")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Location")
                Text("Unavailable")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Connection status
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Connection Status")
                Text("Unknown")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Last Successful Update")
                Text("Unavailable")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sensor readings
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Sensor Status")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Temperature: Unavailable")
                Text("Humidity: Unavailable")
                Text("Pressure: Unavailable")
                Text("Light Level: Unavailable")
            }
        }
    }
}
