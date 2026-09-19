package com.routelens.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stops")
data class StopEntity(
    @PrimaryKey val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

@Entity(tableName = "routes")
data class RouteEntity(
    @PrimaryKey val id: String,
    val routeName: String
)

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey val id: String,
    val routeId: String,
    val startTime: String
)