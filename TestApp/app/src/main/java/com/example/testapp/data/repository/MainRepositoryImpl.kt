package com.example.testapp.data.repository

import com.example.testapp.api.DummyAPI
import com.example.testapp.data.DummyResponse
import com.example.testapp.util.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: DummyAPI
) : MainRepository {
    override suspend fun getData(): Resource<DummyResponse> {
        return try {
            val response = api.getData()
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something Went Wrong")
        }
    }
}