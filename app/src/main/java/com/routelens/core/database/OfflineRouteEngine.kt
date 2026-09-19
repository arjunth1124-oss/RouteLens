package com.routelens.core.database

import com.routelens.core.database.entities.StopEntity
import kotlin.math.*

data class NavigationRoute(
    val origin: StopEntity,
    val destination: StopEntity,
    val totalDistanceKm: Double,
    val estimatedMinutes: Int,
    val instructions: List<String>
)

object OfflineRouteEngine {

    fun calculateDistanceKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }

    fun calculateCustomPath(
        startName: String,
        startLat: Double,
        startLng: Double,
        endName: String,
        endLat: Double,
        endLng: Double
    ): NavigationRoute {
        val originEntity = StopEntity("origin", startName, startLat, startLng)
        val destEntity = StopEntity("dest", endName, endLat, endLng)

        val totalDist = calculateDistanceKm(startLat, startLng, endLat, endLng)
        val etaMinutes = max(1, (totalDist / 50.0 * 60).toInt()) // ~50 km/h average drive speed

        val instructions = listOf(
            "Head north from $startName",
            "Merge onto main expressway toward $endName",
            "Continue straight for ${(totalDist * 0.8).toInt()} km",
            "Take exit towards $endName",
            "Arrive at destination: $endName"
        )

        return NavigationRoute(
            origin = originEntity,
            destination = destEntity,
            totalDistanceKm = (totalDist * 100).roundToInt() / 100.0,
            estimatedMinutes = etaMinutes,
            instructions = instructions
        )
    }
}