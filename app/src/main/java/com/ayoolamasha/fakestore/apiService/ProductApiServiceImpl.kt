package com.ayoolamasha.fakestore.apiService

import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductApiServiceImpl @Inject constructor(private val productApiService: ProductApiService) {

    suspend fun getProduct() : Response<List<GetProducts>> {
        return productApiService.getProducts()

    }

    suspend fun getProductDetails(productId: Int): Response<GetProducts>{
        return productApiService.getProductDetails(id = productId)
    }
}