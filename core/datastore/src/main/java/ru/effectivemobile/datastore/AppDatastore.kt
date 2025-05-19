package ru.effectivemobile.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object AppDatastore {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = KEY_APP_DATASTORE)


    private const val KEY_APP_DATASTORE = "Thousand Courses"

    const val KEY_IS_AUTHORIZED = "is_authorized"
}