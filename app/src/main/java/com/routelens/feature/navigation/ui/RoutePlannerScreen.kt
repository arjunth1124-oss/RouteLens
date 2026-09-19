package com.routelens.feature.navigation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutePlannerScreen(
    viewModel: NavigationViewModel,
    onStartNavigation: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    var showPresetDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E3DF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Route Planner",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Origin Input
            OutlinedTextField(
                value = uiState.originName,
                onValueChange = { viewModel.updateOrigin(it, uiState.originLat, uiState.originLng) },
                label = { Text("Starting Location") },
                leadingIcon = { Icon(Icons.Default.MyLocation, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Destination Input
            OutlinedTextField(
                value = uiState.destinationName,
                onValueChange = { viewModel.updateDestination(it, uiState.destinationLat, uiState.destinationLng) },
                label = { Text("Destination") },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Mode Selector Chips
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val modes = listOf(
                    "Drive" to Icons.Default.DirectionsCar,
                    "Transit" to Icons.Default.DirectionsBus,
                    "Walk" to Icons.Default.DirectionsWalk
                )

                modes.forEach { (mode, icon) ->
                    val isSelected = uiState.transportMode == mode
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.setTransportMode(mode) },
                        label = { Text(mode) },
                        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFFE8F0FE),
                            selectedLabelColor = Color(0xFF1A73E8),
                            selectedLeadingIconColor = Color(0xFF1A73E8)
                        )
                    )
                }
            }

            Button(
                onClick = { showPresetDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F6368))
            ) {
                Text("Select Sample Presets")
            }

            Spacer(modifier = Modifier.weight(1f))

            // Start Navigation Button
            Button(
                onClick = onStartNavigation,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8))
            ) {
                Text(
                    text = "Start Navigation",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Preset Selector Dialog
        if (showPresetDialog) {
            AlertDialog(
                onDismissRequest = { showPresetDialog = false },
                title = { Text("Preset Routes") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "1. New York to Los Angeles (USA)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.updateOrigin("New York, USA", 40.7128, -74.0060)
                                    viewModel.updateDestination("Los Angeles, USA", 34.0522, -118.2437)
                                    showPresetDialog = false
                                }
                                .padding(8.dp)
                        )
                        Text(
                            text = "2. London to Paris (Europe)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.updateOrigin("London, UK", 51.5074, -0.1278)
                                    viewModel.updateDestination("Paris, France", 48.8566, 2.3522)
                                    showPresetDialog = false
                                }
                                .padding(8.dp)
                        )
                        Text(
                            text = "3. Tokyo to Kyoto (Japan)",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.updateOrigin("Tokyo, Japan", 35.6762, 139.6503)
                                    viewModel.updateDestination("Kyoto, Japan", 35.0116, 135.7681)
                                    showPresetDialog = false
                                }
                                .padding(8.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showPresetDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}