
package com.example.urcast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.annotation.DrawableRes
import com.example.urcast.R

// Weather node data model
data class WeatherNode(
    val name: String,
    val temperature: Double,
    val humidity: Int,
    val pressure: Int,
    val lightLevel: String,
    val lastRecorded: String,
    @DrawableRes val iconResId: Int
)

// Sample weather nodes
val sampleNodes = listOf(
    WeatherNode(
        name = "Dundalk Node",
        temperature = 14.0,
        humidity = 72,
        pressure = 1013,
        lightLevel = "Available",
        lastRecorded = "10:20 AM",
        iconResId = R.drawable.cloudy_day_1
    ),
    WeatherNode(
        name = "Drogheda Node",
        temperature = 16.2,
        humidity = 65,
        pressure = 1015,
        lightLevel = "High",
        lastRecorded = "10:15 AM",
        iconResId = R.drawable.day
    ),
    WeatherNode(
        name = "Dublin Node",
        temperature = 13.8,
        humidity = 80,
        pressure = 1009,
        lightLevel = "Low",
        lastRecorded = "10:22 AM",
        iconResId = R.drawable.rainy_1
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    var selectedScreen by remember { mutableStateOf("Home") }
    val nodes = sampleNodes

    var selectedNode by remember {
        mutableStateOf(nodes[0])
    }

    var nodeMenuExpanded by remember {
        mutableStateOf(false)
    }

    var isLoggedIn by remember { mutableStateOf(false) }
    var activeUser by remember { mutableStateOf("Guest User") }

    // Settings preferences
    var fontSize by remember { mutableStateOf("Medium") }
    var highContrast by remember { mutableStateOf(false) }
    var temperatureUnit by remember { mutableStateOf("°C") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Box {
                        TextButton(
                            onClick = {
                                nodeMenuExpanded = true
                            }
                        ) {
                            Text(
                                text = selectedNode.name,
                                maxLines = 1
                            )

                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "Select weather node"
                            )
                        }

                        DropdownMenu(
                            expanded = nodeMenuExpanded,
                            onDismissRequest = {
                                nodeMenuExpanded = false
                            }
                        ) {
                            nodes.forEach { node ->
                                DropdownMenuItem(
                                    text = {
                                        Text(node.name)
                                    },
                                    onClick = {
                                        selectedNode = node
                                        nodeMenuExpanded = false
                                        selectedScreen = "Home"
                                    }
                                )
                            }
                        }
                    }
                },
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
                    IconButton(
                        onClick = {
                            selectedScreen = "Settings"
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
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
                    selected = selectedScreen == "AI Chat",
                    onClick = { selectedScreen = "AI Chat" },
                    icon = {
                        Icon(Icons.Filled.Chat, contentDescription = "AI Chat")
                    },
                    label = { Text("AI Chat") }
                )

                NavigationBarItem(
                    selected = selectedScreen == "History",
                    onClick = { selectedScreen = "History" },
                    icon = {
                        Icon(Icons.Filled.ShowChart, contentDescription = "History")
                    },
                    label = { Text("History") }
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
                "Home" -> HomeScreen(node = selectedNode)

                "Map" -> MapScreen()

                "AI Chat" -> AIChatScreen()

                "History" -> HistoryScreen()

                "My Nodes" -> NodeListScreen()

                "Profile" -> ProfileScreen(
                    isLoggedIn = isLoggedIn,
                    username = activeUser,
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
                    onFontSizeChange = { newFontSize ->
                        fontSize = newFontSize
                    },
                    highContrast = highContrast,
                    onHighContrastChange = { newHighContrast ->
                        highContrast = newHighContrast
                    },
                    temperatureUnit = temperatureUnit,
                    onTemperatureUnitChange = { newUnit ->
                        temperatureUnit = newUnit
                    }
                )

            }
        }
    }
}
