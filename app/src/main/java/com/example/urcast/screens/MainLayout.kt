package com.example.urcast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.urcast.R
import com.example.urcast.WeatherNode
import com.example.urcast.viewmodel.NodeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    fontSize: String,
    onFontSizeChange: (String) -> Unit,
    highContrast: Boolean,
    onHighContrastChange: (Boolean) -> Unit,
    temperatureUnit: String,
    onTemperatureUnitChange: (String) -> Unit
) {
    var selectedScreen by remember { mutableStateOf("Home") }

    val nodeViewModel: NodeViewModel = viewModel()
    val uiState by nodeViewModel.uiState.collectAsState()

    val nodes = remember(uiState.nodes, uiState.readings) {
        uiState.nodes.map { apiNode ->
            val latestReading = uiState.readings
                .filter { it.node_id == apiNode.node_id }
                .maxByOrNull { it.recorded_at }

            WeatherNode(
                name = apiNode.node_name,
                temperature = latestReading?.temperature ?: 0.0,
                humidity = latestReading?.humidity?.toInt() ?: 0,
                pressure = latestReading?.pressure?.toInt() ?: 0,
                lightLevel = latestReading?.light_level?.toInt() ?: 0,
                lastRecorded = latestReading?.recorded_at ?: "No readings yet",
                iconFileName = "clear_day",
                latitude = apiNode.latitude ?: 0.0,
                longitude = apiNode.longitude ?: 0.0
            )
        }
    }

    var selectedNodeName by remember { mutableStateOf<String?>(null) }

    val selectedNode = nodes.firstOrNull {
        it.name == selectedNodeName
    } ?: nodes.firstOrNull()

    var isLoggedIn by remember { mutableStateOf(false) }
    var activeUser by remember { mutableStateOf("Guest User") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(
                            id = if (isDarkTheme) {
                                R.drawable.urcast_logo_dark
                            } else {
                                R.drawable.urcast_logo_light
                            }
                        ),
                        contentDescription = "urcast logo",
                        modifier = Modifier.height(60.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                actions = {
                    IconButton(onClick = { selectedScreen = "Settings" }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen == "Home",
                    onClick = { selectedScreen = "Home" },
                    icon = {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedScreen == "Map",
                    onClick = { selectedScreen = "Map" },
                    icon = {
                        Icon(Icons.Filled.Place, contentDescription = "Map")
                    },
                    label = { Text("Map") }
                )

                NavigationBarItem(
                    selected = selectedScreen == "Profile",
                    onClick = { selectedScreen = "Profile" },
                    icon = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedScreen) {
                "Home" -> {
                    when {
                        uiState.isLoading -> {
                            CircularProgressIndicator()
                        }

                        uiState.errorMessage != null -> {
                            Text("Error: ${uiState.errorMessage}")
                        }

                        selectedNode != null -> {
                            HomeScreen(
                                node = selectedNode,
                                nodes = nodes,
                                onNodeSelected = {
                                    selectedNodeName = it.name
                                },
                                temperatureUnit = temperatureUnit
                            )
                        }

                        else -> {
                            Text("No weather stations found.")
                        }
                    }
                }

                "Map" -> MapScreen()

                "Profile" -> ProfileScreen(
                    isLoggedIn = isLoggedIn,
                    username = activeUser,
                    temperatureUnit = temperatureUnit,
                    onLoginSuccess = { user ->
                        activeUser = user
                        isLoggedIn = true
                    },
                    onLogout = {
                        isLoggedIn = false
                        activeUser = "Guest User"
                    }
                )

                "Settings" -> SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChange = onThemeChange,
                    fontSize = fontSize,
                    onFontSizeChange = onFontSizeChange,
                    highContrast = highContrast,
                    onHighContrastChange = onHighContrastChange,
                    temperatureUnit = temperatureUnit,
                    onTemperatureUnitChange = onTemperatureUnitChange
                )
            }
        }
    }
}