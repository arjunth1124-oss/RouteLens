package com.routelens.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.routelens.core.database.dao.TransitDao
import com.routelens.core.database.entities.RouteEntity
import com.routelens.core.database.entities.StopEntity
import com.routelens.core.database.entities.TripEntity

@Database(
    entities = [
        StopEntity::class,
        RouteEntity::class,
        TripEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transitDao(): TransitDao
}