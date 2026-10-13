package com.example.urcast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.urcast.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth

@Composable
fun LoginScreen(onGuestLogin: () -> Unit) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            TextButton(
//                onClick = {
//                    // Sign up as contributor
//                }
//            ) {
//                Text("Sign Up as Contributor")
//            }
//        }

        // Login content
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.urcast_logo),
                contentDescription = "urcast logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = {
                    Text("Username")
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text("Password")
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Login */ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = buttonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF176B70),
                    contentColor = Color.White
                )
            ) {
                Text("Log In")
            }

            Button(
                onClick = { onGuestLogin() },
                modifier = Modifier
                    .fillMaxWidth(),
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
        }
    }
}