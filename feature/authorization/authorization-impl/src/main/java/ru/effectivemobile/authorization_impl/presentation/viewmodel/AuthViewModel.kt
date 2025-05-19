package ru.effectivemobile.authorization_impl.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.effectivemobile.datastore.AppDatastore
import ru.effectivemobile.datastore.AppDatastore.dataStore

internal class AuthViewModel : ViewModel() {

    suspend fun logIn(email: String, password: String): Boolean { //заглушка т.к. нет сервера
        val isSuccess = viewModelScope.async(Dispatchers.IO) { true }.await()
        return isSuccess
    }

    fun saveAuthState(context: Context) {
        viewModelScope.launch {
            val isAuthorizedKey = booleanPreferencesKey(AppDatastore.KEY_IS_AUTHORIZED)
            context.dataStore.edit { preferences ->
                preferences[isAuthorizedKey] = true
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        val res = email.matches(emailRegex.toRegex())
        return res
    }

    fun openVk(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri())
        context.startActivity(intent)
    }

    fun openOk(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())
        context.startActivity(intent)
    }
}