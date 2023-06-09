package com.example.testapp.data.repository

import com.example.testapp.data.DummyResponse
import com.example.testapp.util.Resource

interface MainRepository {
    suspend fun getData(): Resource<DummyResponse>
}