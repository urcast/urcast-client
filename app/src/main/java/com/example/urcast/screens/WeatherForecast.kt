
package com.example.urcast.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.urcast.R

private data class ForecastPeriod(
    val title: String,
    val subtitle: String,
    val labels: List<String>,
    val values: List<String>,
    val icons: List<Int>
)

private val forecastPeriods = listOf(

    ForecastPeriod(
        title = "7 Hours Forecast",
        subtitle = "Expected temperature",
        labels = listOf("15h", "16h", "17h", "18h", "19h", "20h", "21h"),
        values = listOf("17°", "16°", "15°", "14°", "13°", "12°", "12°"),
        icons = listOf(
            R.drawable.clear_day,
            R.drawable.cloudy_1_day,
            R.drawable.cloudy,
            R.drawable.rainy_1,
            R.drawable.rainy_1,
            R.drawable.clear_night,
            R.drawable.clear_night
        )
    ),

    ForecastPeriod(
        title = "7 Days Forecast",
        subtitle = "Expected daily temperature",
        labels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        values = listOf("16°", "17°", "15°", "14°", "18°", "17°", "16°"),
        icons = listOf(
            R.drawable.clear_day,
            R.drawable.cloudy_1_day,
            R.drawable.rainy_1_day,
            R.drawable.rainy_3_day,
            R.drawable.clear_day,
            R.drawable.cloudy,
            R.drawable.clear_day
        )
    )
)

@Composable
fun WeatherForecast(page: Int) {
    val period = forecastPeriods[page.coerceIn(0, 1)]

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            period.labels.forEachIndexed { index, label ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        painter = painterResource(
                            id = period.icons.getOrElse(index) {
                                R.drawable.clear_day
                            }
                        ),
                        contentDescription = "Weather condition",
                        modifier = Modifier.size(36.dp),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = period.values.getOrElse(index) { "" },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
