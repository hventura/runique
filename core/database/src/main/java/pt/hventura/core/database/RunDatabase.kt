package pt.hventura.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.hventura.core.database.dao.RunDao
import pt.hventura.core.database.entity.RunEntity

@Database(
    entities = [
        RunEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
}