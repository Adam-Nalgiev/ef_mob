package ru.effectivemobile.main_impl.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class Client @Inject constructor() {

    val retrofit: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    companion object {
        private const val BASE_URL = "https://drive.usercontent.google.com/"
    }
}