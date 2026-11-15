package com.example.urcast.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.urcast.data.remote.dto.NodeDto
import com.example.urcast.data.remote.dto.ReadingDto
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MapScreen(
    nodes: List<NodeDto>,
    readings: List<ReadingDto>
) {
    val context = LocalContext.current

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            hasLocationPermission =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        }

    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    var selectedNode by remember {
        mutableStateOf<NodeDto?>(null)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(53.85, -6.35),
            8f
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            try {
                val locationClient =
                    LocationServices.getFusedLocationProviderClient(context)

                locationClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        scope.launch {
                            cameraPositionState.animate(
                                CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        location.latitude,
                                        location.longitude
                                    ),
                                    9f
                                )
                            )
                        }
                    }
                }
            } catch (e: SecurityException) {
                Log.e("UrCastMap", "Location permission unavailable", e)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = hasLocationPermission
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = hasLocationPermission,
                zoomControlsEnabled = true,
                zoomGesturesEnabled = true
            ),
            onMapClick = {
                selectedNode = null
            }
        ) {
            nodes.forEach { node ->
                val latitude = node.latitude
                val longitude = node.longitude

                if (latitude != null && longitude != null) {
                    val latestReading = readings
                        .filter { it.node_id == node.node_id }
                        .maxByOrNull { it.recorded_at }

                    val isSelected = selectedNode?.node_id == node.node_id
                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(latitude, longitude)
                        ),
                        title = node.node_name,
                        snippet = latestReading?.temperature?.let {
                            "$it°C"
                        } ?: "No readings available",
                        icon = BitmapDescriptorFactory.defaultMarker(
                            if (isSelected) {
                                BitmapDescriptorFactory.HUE_BLUE
                            } else {
                                BitmapDescriptorFactory.HUE_RED
                            }
                        ),
                        onClick = {
                            selectedNode = node
                            true
                        },
                        zIndex = if (isSelected) 1f else 0f
                    )
                }
            }
        }

        selectedNode?.let { node ->

            val latestReading = readings
                .filter { it.node_id == node.node_id }
                .maxByOrNull { it.recorded_at }

            val timeAgo = latestReading?.let {
                getTimeAgo(it.recorded_at)
            }

            val minutesAgo = latestReading?.let {
                try {
                    Duration.between(
                        LocalDateTime.parse(it.recorded_at),
                        LocalDateTime.now()
                    ).toMinutes()
                } catch (e: Exception) {
                    Log.e("UrCastMap", "Could not parse node reading timestamp: ${it.recorded_at}", e)
                    Long.MAX_VALUE
                }
            } ?: Long.MAX_VALUE

            val isOnline = minutesAgo >= 0 && minutesAgo <= 15

            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = node.node_name,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.DarkGray
                        )

                        Text(
                            text = if (isOnline) "+ Online" else "- Offline",
                            color = if (isOnline) {
                                Color(0xFF2E7D32)
                            } else {
                                Color(0xFFC62828)
                            }
                        )
                    }

                    Text(
                        text = if (latestReading != null) {
                            "Last available: $timeAgo"
                        } else {
                            "No readings available"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Temperature: ${
                            latestReading?.temperature?.let { "$it°C" } ?: "N/A"
                        }"
                    )

                    Text(
                        "Humidity: ${
                            latestReading?.humidity?.let { "$it%" } ?: "N/A"
                        }"
                    )

                    Text(
                        "Pressure: ${
                            latestReading?.pressure?.let { "$it hPa" } ?: "N/A"
                        }"
                    )

                    Text(
                        "Light level: ${
                            latestReading?.light_level?.let { "$it lux" } ?: "N/A"
                        }"
                    )
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun getTimeAgo(recordedAt: String): String {
    return try {
        val readingTime = LocalDateTime.parse(recordedAt)

        val minutes = Duration.between(
            readingTime,
            LocalDateTime.now()
        ).toMinutes()

        when {
            minutes < 0 -> "just now"
            minutes < 1 -> "just now"
            minutes < 60 -> "$minutes minutes ago"
            minutes < 1440 -> "${minutes / 60} hours ago"
            else -> "${minutes / 1440} days ago"
        }
    } catch (e: Exception) {
        println("URCAST TIMESTAMP ERROR: ${e.message}")
        "Unknown"
    }
}