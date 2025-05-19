package ru.effectivemobile.main_impl.data.network

import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url
import ru.effectivemobile.main_impl.data.model.dto.Response

internal interface Api {

    /** Никакой пагинаци или заморочек по оптимизации, т.к. список конечный и очень малый - нет смысла в усложнении*/
    @GET
    @Streaming
    suspend fun getCoursesList(
        @Url url: String = "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download",
    ): Response
}