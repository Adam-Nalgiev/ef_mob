package ru.effectivemobile.favorites_impl.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.effectivemobile.favorites_impl.data.model.dbo.CourseDbo

@Dao
internal interface Dao {

    @Query("SELECT * FROM courses")
    suspend fun getAll(): List<CourseDbo>

    @Query("SELECT * FROM courses WHERE has_like = 1")
    suspend fun getFavorites(): List<CourseDbo>

    @Query("DELETE FROM courses WHERE id = :id")
    suspend fun delete(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: CourseDbo)
}