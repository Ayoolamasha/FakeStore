package com.ayoolamasha.fakestore.cache

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDaoImpl @Inject constructor(
    private val productDao: ProductDao
){
    suspend fun insertProduct(productsEntity: List<ProductsEntity>){
        return productDao.insertProducts(productsEntity = productsEntity)
    }

    fun getAllProducts(): List<ProductsEntity>{
        return productDao.getAllProducts()
    }

    fun getProductById(productId: Int): ProductsEntity{
        return productDao.getProductById(productId = productId)
    }
}