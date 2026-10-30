package com.example.urcast.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private data class WeatherPeriod(
    val title: String,
    val subtitle: String,
    val labels: List<String>,
    val values: List<String>
)

private val weatherPeriods = listOf(
    WeatherPeriod(
        "7 Days History",
        "Historical temperature",
        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        listOf("14°", "16°", "13°", "15°", "17°", "12°", "15°")
    ),
    WeatherPeriod(
        "7 Hours History",
        "Recent temperature readings",
        listOf("08h", "09h", "10h", "11h", "12h", "13h", "14h"),
        listOf("12°", "13°", "14°", "15°", "16°", "16°", "17°")
    ),
    WeatherPeriod(
        "7 Hours Forecast",
        "Expected temperature",
        listOf("15h", "16h", "17h", "18h", "19h", "20h", "21h"),
        listOf("17°", "16°", "15°", "14°", "13°", "12°", "12°")
    ),
    WeatherPeriod(
        "7 Days Forecast",
        "Expected daily temperature",
        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"),
        listOf("16°", "17°", "15°", "14°", "18°", "17°", "16°")
    )
)

@Composable
fun ForecastHistoryTiles(
    onPeriodSelected: (Int) -> Unit = {}
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { weatherPeriods.size }
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        onPeriodSelected(pagerState.currentPage)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Weather Overview",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val period = weatherPeriods[page]

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = period.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = period.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

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

                                Box(
                                    modifier = Modifier
                                        .height(36.dp)
                                        .fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .height(36.dp)
                                            .width(1.dp)
                                            .background(
                                                MaterialTheme.colorScheme
                                                    .outlineVariant
                                            )
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = period.values[index],
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Four clickable page indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            weatherPeriods.indices.forEach { index ->
                val selected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .height(8.dp)
                        .then(
                            if (selected) Modifier.weight(1f, fill = false)
                            else Modifier
                        )
                        .background(
                            color = if (selected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.outlineVariant
                            },
                            shape = CircleShape
                        )
                        .then(
                            if (selected) {
                                Modifier
                            } else {
                                Modifier
                            }
                        )
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(horizontal = 0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(if (selected) 20.dp else 8.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (selected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.outlineVariant
                            )
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                    )
                }
            }
        }
    }
}
