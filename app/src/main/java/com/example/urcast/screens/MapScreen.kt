package com.example.urcast.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.urcast.data.remote.dto.NodeDto
import com.example.urcast.data.remote.dto.ReadingDto
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

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
                                    13f
                                )
                            )
                        }
                    }
                }
            } catch (e: SecurityException) {
                // Location permission is unavailable.
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Nearby weather nodes",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = hasLocationPermission
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = hasLocationPermission
            )
        ) {
            nodes.forEach { node ->

                val latitude = node.latitude
                val longitude = node.longitude

                if (latitude != null && longitude != null) {
                    val latestReading = readings
                        .filter { it.node_id == node.node_id }
                        .maxByOrNull { it.recorded_at }

                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(latitude, longitude)
                        ),
                        title = node.node_name,
                        snippet = latestReading?.temperature?.let {
                            "$it°C"
                        } ?: "No readings available",
                        onClick = {
                            selectedNode = node
                            true
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        selectedNode?.let { node ->

            val latestReading = readings
                .filter { it.node_id == node.node_id }
                .maxByOrNull { it.recorded_at }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = node.node_name,
                        style = MaterialTheme.typography.titleMedium
                    )

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
        } ?: Text(
            text = "Select a marker to view weather information."
        )
    }
}