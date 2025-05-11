package ru.effectivemobile.authorization_impl.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

internal class AuthViewModel : ViewModel() {

    suspend fun logIn(email: String, password: String): Boolean { //заглушка т.к. нет сервера
        val isSuccess = viewModelScope.async(Dispatchers.IO) { true }.await()
        return isSuccess
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$+."
        return email.matches(emailRegex.toRegex())
    }

    fun openVk(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
        context.startActivity(intent)
    }

    fun openOk(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
        context.startActivity(intent)
    }
}