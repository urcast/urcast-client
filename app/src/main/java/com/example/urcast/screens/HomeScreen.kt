
package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urcast.WeatherNode

@Composable
fun HomeScreen(
    node: WeatherNode,
    nodes: List<WeatherNode>,
    onNodeSelected: (WeatherNode) -> Unit,
    temperatureUnit: String
) {
    var nodeMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Weather station selector
        Box {
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { nodeMenuExpanded = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            "SELECT WEATHER STATION",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            node.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "Select weather station"
                    )
                }
            }

            DropdownMenu(
                expanded = nodeMenuExpanded,
                onDismissRequest = {
                    nodeMenuExpanded = false
                }
            ) {
                nodes.forEach { availableNode ->
                    DropdownMenuItem(
                        text = { Text(availableNode.name) },
                        onClick = {
                            onNodeSelected(availableNode)
                            nodeMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        WeatherDashboardScreen(
            node = node,
            temperatureUnit = temperatureUnit
        )

        Spacer(modifier = Modifier.height(16.dp))

        MapScreen()
    }
}
