package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    isLoggedIn: Boolean,
    username: String,
    onLoginSuccess: (String) -> Unit,
    onLogout: () -> Unit
) {
    // Controls whether RegisterNodeScreen is shown
    var isRegisteringNode by remember { mutableStateOf(false) }

    if (!isLoggedIn) {
        // --- LOGGED OUT STATE: SHOW LOGIN ---
        LoginScreen(
            onGuestLogin = { onLoginSuccess("Guest User") },
            onLoginSuccess = { user -> onLoginSuccess(user) }
        )
    } else if (isRegisteringNode) {
        // --- REGISTER NODE SCREEN STATE ---
        RegisterNodeScreen(
            onNodeRegistered = {
                // Return to profile dashboard once registered
                isRegisteringNode = false
            },
            onCancel = {
                // Return to profile dashboard without registering
                isRegisteringNode = false
            }
        )
    } else {
        // --- LOGGED IN STATE: SHOW USER PROFILE & NODES ---
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // 1. Account Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = username,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (username == "Guest User") "Viewer Account" else "Contributor",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Log out",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 2. User Registered Nodes Section
            Text(
                text = "MY WEATHER NODES",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Node List
            sampleNodes.forEach { node ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Cloud,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = node.name, fontWeight = FontWeight.Bold)
                            Text(
                                text = "Last active: ${node.lastRecorded}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = "${node.temperature.toInt()}°C",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Button to Add New Node
            Button(
                onClick = { isRegisteringNode = true }, // Navigates to RegisterNodeScreen
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register New Weather Node")
            }
        }
    }
}