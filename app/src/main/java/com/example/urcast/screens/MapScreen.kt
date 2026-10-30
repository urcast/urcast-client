package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.example.urcast.WeatherNode
import com.example.urcast.sampleNodes

@Composable
fun MapScreen() {

    var selectedNode by remember {
        mutableStateOf<WeatherNode?>(null)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(53.85, -6.35),
            8f
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Nearby weather nodes",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            cameraPositionState = cameraPositionState
        ) {
            sampleNodes.forEach { node ->

                Marker(
                    state = rememberMarkerState(
                        position = LatLng(
                            node.latitude,
                            node.longitude
                        )
                    ),
                    title = node.name,
                    snippet = "${node.temperature}°C",
                    onClick = {
                        selectedNode = node
                        true
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        selectedNode?.let { node ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = node.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("Temperature: ${node.temperature}°C")
                    Text("Humidity: ${node.humidity}%")
                }
            }
        } ?: Text(
            text = "Select a marker to view weather information."
        )
    }
}