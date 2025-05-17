package ru.effectivemobile.main_impl.di.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.effectivemobile.main_impl.data.database.Dao
import ru.effectivemobile.main_impl.data.database.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "Thousands Courses Database"
        ).build()
    }

    @Provides
    fun provideDao(database: Database): Dao {
        return database.dao()
    }
}