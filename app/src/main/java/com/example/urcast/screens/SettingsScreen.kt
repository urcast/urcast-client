package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.urcast.NotificationHelper

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    fontSize: String,
    onFontSizeChange: (String) -> Unit,
    highContrast: Boolean,
    onHighContrastChange: (Boolean) -> Unit,
    temperatureUnit: String,
    onTemperatureUnitChange: (String) -> Unit
) {
    val context = LocalContext.current
    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                NotificationHelper.showTestNotification(context)
            }
        }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Dark mode
        SettingRow(
            title = "Dark Mode",
            description = "Use a dark theme throughout the app"
        ) {
            Switch(
                checked = isDarkTheme,
                onCheckedChange = onThemeChange
            )
        }

        HorizontalDivider()

        // Font size
        Text(
            text = "Font Size",
            style = MaterialTheme.typography.titleMedium
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("Small", "Medium", "Large").forEachIndexed { index, size ->
                SegmentedButton(
                    selected = fontSize == size,
                    onClick = { onFontSizeChange(size) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = 3
                    )
                ) {
                    Text(size)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()

        // High contrast
        SettingRow(
            title = "High Contrast",
            description = "Improve text and interface visibility"
        ) {
            Switch(
                checked = highContrast,
                onCheckedChange = onHighContrastChange
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()

        // Temperature unit
        Text(
            text = "Temperature Unit",
            style = MaterialTheme.typography.titleMedium
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("°C", "°F").forEachIndexed { index, unit ->
                SegmentedButton(
                    selected = temperatureUnit == unit,
                    onClick = { onTemperatureUnitChange(unit) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = 2
                    )
                ) {
                    Text(unit)
                }
            }
        }
        HorizontalDivider()

        Spacer(modifier = Modifier.height(8.dp))

        NotificationSettings()
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    notificationPermissionLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                } else {
                    NotificationHelper.showTestNotification(context)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Test notification")
        }
    }
}
@Composable
private fun SettingRow(
    title: String,
    description: String,
    control: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        control()
    }
}