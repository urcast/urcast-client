package com.example.urcast.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherDashboardScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // App name
        Text(
            text = "urcast"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Page title
        Text(
            text = "Local Weather"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Main temperature
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = " // input temperature here "
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Current Temperature"
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Weather readings
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Humidity")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = " // input humidity here "
                    )
                }
            }

            Card(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Light")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = " // input light here "
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Location
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Location")

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = " // input location here "
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Last updated
        Text(
            text = " // input timestamp here "
        )
    }
}