package com.example.urcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.urcast.screens.LoginScreen
import com.example.urcast.screens.WeatherDashboardScreen
import com.example.urcast.ui.theme.UrcastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            UrcastTheme {

                var showDashboard by remember { mutableStateOf(false) }

                if (showDashboard) {
                    WeatherDashboardScreen()
                } else {
                    LoginScreen(
                        onGuestLogin = {
                            showDashboard = true
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    UrcastTheme {
        LoginScreen(
            onGuestLogin = {}
        )
    }
}