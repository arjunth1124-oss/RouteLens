package com.routelens.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.routelens.core.database.entities.RouteEntity
import com.routelens.core.database.entities.StopEntity
import com.routelens.core.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransitDao {

    @Query("SELECT * FROM stops WHERE LOWER(name) LIKE LOWER(:query)")
    fun searchStops(query: String): Flow<List<StopEntity>>

    @Query("SELECT * FROM stops WHERE id = :stopId LIMIT 1")
    suspend fun getStopById(stopId: String): StopEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStop(stop: StopEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStops(stops: List<StopEntity>)

    @Query("SELECT * FROM routes")
    fun getAllRoutes(): Flow<List<RouteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoute(route: RouteEntity)

    @Query("SELECT * FROM trips WHERE routeId = :routeId")
    fun getTripsForRoute(routeId: String): Flow<List<TripEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)
}