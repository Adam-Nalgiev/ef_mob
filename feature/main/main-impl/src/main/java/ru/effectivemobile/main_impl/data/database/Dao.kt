package ru.effectivemobile.main_impl.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.effectivemobile.main_impl.data.model.dbo.CourseDbo

@Dao
interface Dao {

    @Query("SELECT * FROM courses")
    suspend fun getAll(): List<CourseDbo>

    @Query("SELECT * FROM courses WHERE has_like = 1")
    suspend fun getFavorites(): List<CourseDbo>

    @Query("DELETE FROM courses WHERE _id = :id")
    suspend fun delete(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: CourseDbo)
}