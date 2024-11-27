package com.ayoolamasha.fakestore.apiService

import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<GetProducts>>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Response<GetProducts>
}