package ru.effectivemobile.main_impl.data.model.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.effectivemobile.main_impl.entity.Course

@Entity(tableName = "courses")
data class CourseDbo(
    @PrimaryKey
    @ColumnInfo("id")
    val _id: Int? = null,

    @ColumnInfo("_id")
    override val id: String,

    @ColumnInfo("title")
    override val title: String,

    @ColumnInfo("text")
    override val text: String,

    @ColumnInfo("price")
    override val price: String,

    @ColumnInfo("rate")
    override val rate: Double,

    @ColumnInfo("start_date")
    override val startDate: String,

    @ColumnInfo("has_like")
    override val hasLike: Boolean,

    @ColumnInfo("publish_date")
    override val publishDate: String
) : Course
