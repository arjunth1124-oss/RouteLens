package com.routelens.feature.navigation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NavigationRoute(
    val routeId: String = "",
    val totalDistanceKm: Double = 0.0,
    val estimatedMinutes: Int = 0,
    val instructions: List<String> = emptyList()
)

data class GoogleMapsUiState(
    val originName: String = "Your Location",
    val originLat: Double = 28.6139,
    val originLng: Double = 77.2090,
    val destinationName: String = "Taj Mahal, Agra",
    val destinationLat: Double = 27.1751,
    val destinationLng: Double = 78.0421,
    val activeRoute: NavigationRoute? = null,
    val transportMode: String = "Drive",
    val selectedStop: Any? = null
)

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(GoogleMapsUiState())
    val uiState: StateFlow<GoogleMapsUiState> = _uiState.asStateFlow()

    init {
        loadDefaultRoute()
    }

    private fun loadDefaultRoute() {
        viewModelScope.launch {
            val sampleRoute = NavigationRoute(
                routeId = "route_1",
                totalDistanceKm = 233.0,
                estimatedMinutes = 210,
                instructions = listOf(
                    "Head south toward Yamuna Expressway",
                    "Take the ramp onto Yamuna Expressway",
                    "Continue straight for 180 km",
                    "Take exit toward Agra Bypass",
                    "Arrive at destination"
                )
            )
            _uiState.update { currentState ->
                currentState.copy(activeRoute = sampleRoute)
            }
        }
    }

    fun updateOrigin(name: String, lat: Double, lng: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                originName = name,
                originLat = lat,
                originLng = lng
            )
        }
    }

    fun updateDestination(name: String, lat: Double, lng: Double) {
        _uiState.update { currentState ->
            currentState.copy(
                destinationName = name,
                destinationLat = lat,
                destinationLng = lng
            )
        }
    }

    fun setTransportMode(mode: String) {
        _uiState.update { currentState ->
            currentState.copy(transportMode = mode)
        }
    }

    fun setSelectedStop(stop: Any?) {
        _uiState.update { currentState ->
            currentState.copy(selectedStop = stop)
        }
    }
}