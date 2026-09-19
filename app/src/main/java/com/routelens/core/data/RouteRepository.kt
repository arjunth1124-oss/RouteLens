package com.routelens.core.data

import com.routelens.core.database.dao.TransitDao
import com.routelens.core.model.TransitRoute
import com.routelens.core.model.TransitStop
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RouteRepository @Inject constructor(
    private val transitDao: TransitDao
) {

    suspend fun seedInitialDataIfEmpty() {
        // Handled automatically via Database Callback
    }

    fun searchStops(query: String): Flow<List<TransitStop>> {
        val formattedQuery = "%$query%"
        return transitDao.searchStops(formattedQuery).map { entities ->
            entities.map { TransitStop(it.id, it.name, it.latitude, it.longitude) }
        }
    }

    fun getAvailableRoutes(): Flow<List<TransitRoute>> {
        return transitDao.getAllRoutes().map { routes ->
            routes.map { route ->
                val startStop = transitDao.getStopById(route.id)
                TransitRoute(
                    id = route.id,
                    routeName = route.routeName,
                    routeNumber = "101",
                    startStopName = startStop?.name ?: "Unknown",
                    endStopName = "Terminal"
                )
            }
        }
    }
}