package com.example.urcast.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(node: WeatherNode) {
    // Renders the main dashboard content for the selected node
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        WeatherDashboardScreen(node = node)

        Spacer(modifier = Modifier.height(16.dp))

        MapScreen()

        Spacer(modifier = Modifier.height(16.dp))

        HistoryScreen()
    }

}