package com.example.urcast.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.urcast.screens.NotificationSettings
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urcast.viewmodel.NodeViewModel
import com.example.urcast.data.remote.dto.NodeDto

@Composable
fun ProfileScreen(
    isLoggedIn: Boolean,
    username: String,
    onLoginSuccess: (String) -> Unit,
    onLogout: () -> Unit,
    temperatureUnit: String
) {
    // Controls whether RegisterNodeScreen is shown
    var isRegisteringNode by remember { mutableStateOf(false) }

    val nodeViewModel: NodeViewModel = viewModel()
    val uiState by nodeViewModel.uiState.collectAsState()

    var selectedNode by remember {
        mutableStateOf<NodeDto?>(null)
    }

    if (!isLoggedIn) {

        LoginScreen(
            onGuestLogin = { onLoginSuccess("Guest User") },
            onLoginSuccess = { user ->
                onLoginSuccess(user)
            }
        )

    } else if (isRegisteringNode) {

        RegisterNodeScreen(
            onNodeRegistered = {
                isRegisteringNode = false
            },
            onCancel = {
                isRegisteringNode = false
            }
        )

    } else if (selectedNode != null) {

        RegisteredNodeDetailsScreen(
            node = selectedNode!!,
            onBack = { selectedNode = null }
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
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                    Text("Loading weather nodes...")
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage!!,
                        color = MaterialTheme.colorScheme.error
                    )

                    Button(onClick = { nodeViewModel.fetchNodes() }) {
                        Text("Retry")
                    }
                }

                uiState.nodes.isEmpty() -> {
                    Text("No registered weather nodes found.")
                }

                else -> {
                    uiState.nodes.forEach { node ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .clickable {
                                    selectedNode = node
                                },
                            shape = RoundedCornerShape(16.dp)
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

                                Spacer(Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = node.node_name,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = node.location_name ?: "Location unavailable",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Text(
                                        text = "Status: ${node.status}",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Text(
                                        text = "Last seen: ${node.last_seen ?: "Not recorded"}",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }

                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = "View node details",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp)
            )

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

//TODO feedback after logout