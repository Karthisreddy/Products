package com.example.testapp.data

data class DummyResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)