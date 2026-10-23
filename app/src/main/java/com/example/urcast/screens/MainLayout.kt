
package com.example.urcast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.urcast.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    var selectedScreen by remember { mutableStateOf("Home") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.urcast_logo),
                        contentDescription = "urcast logo",
                        modifier = Modifier.height(40.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                actions = {
                    IconButton(onClick = {
                        selectedScreen = "Profile"
                    }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }

                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = onThemeChange
                    )
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
                    selected = selectedScreen == "My Nodes",
                    onClick = { selectedScreen = "My Nodes" },
                    icon = {
                        Icon(Icons.Filled.Cloud, contentDescription = "My Nodes")
                    },
                    label = { Text("My Nodes") }
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
                "Home" -> WeatherDashboardScreen()
                "Map" -> MapScreen()
                "AI Chat" -> AIChatScreen()
                "History" -> HistoryScreen()
                "My Nodes" -> NodeListScreen()
                "Profile" -> ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    TODO("Not yet implemented")
}

@Composable
fun HistoryScreen() {
    TODO("Not yet implemented")
}

@Composable
fun MapScreen() {
    TODO("Not yet implemented")
}
