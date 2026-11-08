package com.example.urcast.screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urcast.data.remote.dto.NodeDto
import com.example.urcast.viewmodel.AuthViewModel
import com.example.urcast.viewmodel.NodeViewModel

@Composable
fun ProfileScreen(
    isLoggedIn: Boolean,
    username: String,
    onLoginSuccess: (String) -> Unit,
    onLogout: () -> Unit,
    temperatureUnit: String
) {
    // Controls which screen is displayed
    var isRegisteringNode by remember { mutableStateOf(false) }
    var isRegisteringContributor by remember { mutableStateOf(false) }

    // ViewModels
    val nodeViewModel: NodeViewModel = viewModel()
    val nodeState by nodeViewModel.uiState.collectAsState()

    val authViewModel: AuthViewModel = viewModel()
    val authState by authViewModel.uiState.collectAsState()

    // Selected node
    var selectedNode by remember {
        mutableStateOf<NodeDto?>(null)
    }

    // Stores the email used during login
    var loginEmail by remember {
        mutableStateOf("")
    }

    // Handle successful login
    LaunchedEffect(authState.isLoggedIn) {
        if (authState.isLoggedIn) {
            onLoginSuccess(loginEmail)
        }
    }

    // Handle successful contributor registration
    LaunchedEffect(authState.isRegistrationSuccessful) {
        if (authState.isRegistrationSuccessful) {
            isRegisteringContributor = false
            authViewModel.resetState()
        }
    }

    if (!isLoggedIn && isRegisteringContributor) {

        RegisterContributorScreen(
            onRegister = { username, email, password ->
                authViewModel.register(username, email, password)
            },
            onBackToLogin = {
                isRegisteringContributor = false
                authViewModel.resetState()
            },
            isLoading = authState.isLoading,
            errorMessage = authState.errorMessage
        )

    } else if (!isLoggedIn) {

        LoginScreen(
            onLogin = { email, password ->
                loginEmail = email
                authViewModel.login(email, password)
            },
            onSignUp = {
                authViewModel.resetState()
                isRegisteringContributor = true
            },
            isLoading = authState.isLoading,
            errorMessage = authState.errorMessage
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
            onBack = {
                selectedNode = null
            }
        )

    } else {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            // Account Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
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

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = username,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Contributor",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = {
                            authViewModel.resetState()
                            loginEmail = ""
                            onLogout()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Log out",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // User Registered Nodes Section
            Text(
                text = "MY WEATHER NODES",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Node List
            when {
                nodeState.isLoading -> {

                    CircularProgressIndicator()
                    Text("Loading weather nodes...")
                }

                nodeState.errorMessage != null -> {

                    Text(
                        text = nodeState.errorMessage!!,
                        color = MaterialTheme.colorScheme.error
                    )

                    Button(
                        onClick = {
                            nodeViewModel.fetchNodes()
                        }
                    ) {
                        Text("Retry")
                    }
                }

                nodeState.nodes.isEmpty() -> {

                    Text("No registered weather nodes found.")
                }

                else -> {

                    nodeState.nodes.forEach { node ->

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

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

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