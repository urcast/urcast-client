
package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NodeListScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Page heading
        Text("My Weather Nodes")
        Text("View and manage your registered weather nodes.")

        Spacer(modifier = Modifier.height(24.dp))

        // Node 1
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Weather Node 1")
                Text("Location: Unavailable")
                Text("Status: Unknown")
                Text("Last Updated: Unavailable")

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { }) {
                    Text("View Details")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add a node
        Button(onClick = { }) {
            Text("Register New Node")
        }
    }
}
