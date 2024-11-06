package pt.hventura.analytics.data

import pt.hventura.analytics.domain.AnalyticsRepository
import pt.hventura.analytics.domain.AnalyticsValues
import pt.hventura.core.database.dao.AnalyticsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class RoomAnalyticsRepository(
    private val analyticsDao: AnalyticsDao
): AnalyticsRepository {

    override suspend fun getAnalyticsValues(): AnalyticsValues {
        return withContext(Dispatchers.IO) {
            val totalDistance = async { analyticsDao.getTotalDistance() }
            val totalTimeMillis = async { analyticsDao.getTotalTimeRun() }
            val maxRunSpeed = async { analyticsDao.getMaxRunSpeed() }
            val avgDistancePerRun = async { analyticsDao.getAvgDistancePerRun() }
            val avgPacePerRun = async { analyticsDao.getAvgPacePerRun() }

            AnalyticsValues(
                totalDistanceRun = totalDistance.await(),
                totalTimeRun = totalTimeMillis.await().milliseconds,
                fastestEverRun = maxRunSpeed.await(),
                avgDistancePerRun = avgDistancePerRun.await(),
                avgPacePerRun = avgPacePerRun.await()
            )
        }
    }
}