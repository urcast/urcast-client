
package com.example.urcast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.urcast.R
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(
                            id = R.drawable.urcast_logo
                        ),
                        contentDescription = "urcast logo",
                        modifier = Modifier.height(40.dp),
                        contentScale = ContentScale.Fit
                    )
                },
                actions = {
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
                    selected = true,
                    onClick = { /* Home */ },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )


                NavigationBarItem(
                    selected = false,
                    onClick = {
                        // TODO: Open the Chat screen
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Chat,
                            contentDescription = "Chat"
                        )
                    },
                    label = {
                        Text("Chat")
                    }
                )


                NavigationBarItem(
                    selected = false,
                    onClick = { /* Map */ },
                    icon = {
                        Icon(
                            imageVector =
                                androidx.compose.material.icons.Icons.Default.Place,
                            contentDescription = "Map"
                        )
                    },
                    label = { Text("Map") }
                )
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
