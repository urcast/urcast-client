package com.example.urcast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.urcast.R

@Composable
fun LoginScreen(
    onGuestLogin: () -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Hardcoded test user credentials
    val hardcodedUser = "admin"
    val hardcodedPass = "1234"

    val buttonShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.urcast_logo_light),
            contentDescription = "urcast logo",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(120.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                errorMessage = null // Clear error when typing
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = null // Clear error when typing
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Show error message if credentials don't match
        errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (username == hardcodedUser && password == hardcodedPass) {
                    onLoginSuccess(username)
                } else {
                    errorMessage = "Invalid credentials. Try $hardcodedUser / $hardcodedPass"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Log In")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onGuestLogin() },
            modifier = Modifier.fillMaxWidth(),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF176B70),
                contentColor = Color.White
            )
        ) {
            Text("Viewer Login")
        }

        TextButton(
            onClick = { /* Sign up */ },
            shape = buttonShape
        ) {
            Text("Sign Up as Contributor")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("admin, 1234")
    }
}