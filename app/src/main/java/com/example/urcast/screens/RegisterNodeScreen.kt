package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urcast.data.remote.dto.CreateNodeRequest
import com.example.urcast.viewmodel.NodeViewModel
@Composable
fun RegisterNodeScreen(
    onNodeRegistered: () -> Unit,
    onCancel: () -> Unit,
    nodeViewModel: NodeViewModel = viewModel()
){
    var nodeName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by nodeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register New Node",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nodeName,
            onValueChange = { nodeName = it },
            label = { Text("Node Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = latitude,
            onValueChange = { latitude = it },
            label = { Text("Latitude") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = longitude,
            onValueChange = { longitude = it },
            label = { Text("Longitude") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val lat = latitude.toDoubleOrNull()
                val lon = longitude.toDoubleOrNull()


                if (nodeName.isBlank() || location.isBlank()) {
                    errorMessage = "Please complete all fields."
                } else if (
                    lat == null || lon == null ||
                    lat !in -90.0..90.0 ||
                    lon !in -180.0..180.0
                ) {
                    errorMessage = "Please enter valid coordinates."
                } else {
                    errorMessage = null

                    nodeViewModel.registerNode(
                        CreateNodeRequest(
                            node_name = nodeName,
                            latitude = lat,
                            longitude = lon,
                            location_name = location
                        ),
                        onSuccess = onNodeRegistered
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Registration")
        }
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }
        uiState.errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}