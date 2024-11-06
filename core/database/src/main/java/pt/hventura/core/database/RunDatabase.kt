package pt.hventura.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.hventura.core.database.dao.AnalyticsDao
import pt.hventura.core.database.dao.RunDao
import pt.hventura.core.database.dao.RunPendingSyncDao
import pt.hventura.core.database.entity.DeletedRunSyncEntity
import pt.hventura.core.database.entity.RunEntity
import pt.hventura.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
    abstract val analyticsDao: AnalyticsDao

}