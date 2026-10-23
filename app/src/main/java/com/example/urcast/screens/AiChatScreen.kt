package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AIChatScreen() {

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("urcast AI Assistant")
        Text("Ask questions about your local weather.")

        Spacer(modifier = Modifier.height(16.dp))

        Text("Chat messages will appear here.")

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Ask about the weather") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* AI response will be added later */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send")
        }
    }
}
