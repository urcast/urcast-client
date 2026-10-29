package com.example.urcast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Column(
        modifier = Modifier
            .fillMaxSize()
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

