package com.routelens.core.model

data class TransitStop(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

data class TransitRoute(
    val id: String,
    val routeName: String,
    val routeNumber: String,
    val startStopName: String,
    val endStopName: String
)

data class NavigationState(
    val currentLatitude: Double = 0.0,
    val currentLongitude: Double = 0.0,
    val destinationStop: TransitStop? = null,
    val distanceToDestinationMeters: Float = Float.MAX_VALUE,
    val isArrived: Boolean = false,
    val isServiceRunning: Boolean = false
)

sealed interface RouteUiState {
    data object Idle : RouteUiState
    data object Loading : RouteUiState
    data class Success(
        val stops: List<TransitStop>,
        val routes: List<TransitRoute>
    ) : RouteUiState
    data class Error(val message: String) : RouteUiState
}