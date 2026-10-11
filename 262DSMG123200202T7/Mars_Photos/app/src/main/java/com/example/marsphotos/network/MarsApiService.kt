package com.example.marsphotos.network

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))// para admititr cadenas sin codificación
    //cuando llegue el body, sime como convertir ese json en  un dto, pero en este caso en string
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {

    @GET("photos")
    suspend fun getPhotos() : List<MarsPhoto>
}

object MarsApi {
    val retrofitService : MarsApiService by lazy {//no crees este rettrofit de inmediato cuando la app arranque
        retrofit.create(MarsApiService::class.java)//operacion muy costosa en recursos
    }
}