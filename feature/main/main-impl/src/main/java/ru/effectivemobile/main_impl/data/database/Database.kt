package ru.effectivemobile.main_impl.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.effectivemobile.main_impl.data.model.dbo.CourseDbo

@Database(
    entities = [CourseDbo::class],
    version = 1,
    exportSchema = false
)
internal abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao
}