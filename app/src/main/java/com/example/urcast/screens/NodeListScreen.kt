
package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urcast.data.remote.dto.NodeDto

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

@Composable
fun RegisteredNodeDetailsScreen(
    node: NodeDto,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("Back to Nodes")
        }

        Text(
            text = node.node_name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        Text("NODE INFORMATION",
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(Modifier.height(12.dp))

        Text("Node ID: ${node.node_id}")
        Text("Location: ${node.location_name ?: "Not provided"}")
        Text("Status: ${node.status}")
        Text("Installed: ${node.installed_at ?: "Unknown"}")
        Text("Last seen: ${node.last_seen ?: "Not recorded"}")

        Spacer(Modifier.height(20.dp))

        Text(
            "Sensor readings and hardware diagnostics will appear here " +
                    "when their API endpoints are connected."
        )
    }
}
