
package com.example.urcast.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

private data class HistoryPeriod(
    val title: String,
    val subtitle: String,
    val labels: List<String>,
    val rainfallValues: List<Float>
)

private val historyPeriods = listOf(
    HistoryPeriod(
        title = "7 Days History",
        subtitle = "Historical precipitation (mm)",
        labels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        rainfallValues = listOf(0.0f, 1.2f, 4.5f, 2.0f, 0.0f, 6.8f, 3.1f)
    ),
    HistoryPeriod(
        title = "7 Hours History",
        subtitle = "Recent precipitation (mm)",
        labels = listOf("08h", "09h", "10h", "11h", "12h", "13h", "14h"),
        rainfallValues = listOf(0.0f, 0.2f, 1.4f, 2.1f, 0.8f, 0.0f, 0.5f)
    )
)

@Composable
fun WeatherHistory(page: Int) {
    val period = historyPeriods[page.coerceIn(0, 1)]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 12.dp)
    ) {
        Text(
            text = period.title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = period.subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        RainfallBarChart(
            labels = period.labels,
            values = period.rainfallValues
        )
    }
}

@Composable
private fun RainfallBarChart(
    labels: List<String>,
    values: List<Float>
) {
    val maxValue = (values.maxOrNull() ?: 0f).coerceAtLeast(1f)

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            values.forEachIndexed { index, value ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "%.1f".format(value),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        if (value > 0f) {
                            Box(
                                modifier = Modifier
                                    .width(18.dp)
                                    .height((value / maxValue * 40f).dp)
                                    .clip(
                                        RoundedCornerShape(
                                            topStart = 6.dp,
                                            topEnd = 6.dp
                                        )
                                    )
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = labels.getOrElse(index) { "" },
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
