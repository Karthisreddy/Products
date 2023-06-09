package com.example.testapp.api

import com.example.testapp.data.DummyResponse
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPI {
    @GET("/products")
    suspend fun getData(): Response<DummyResponse>
}