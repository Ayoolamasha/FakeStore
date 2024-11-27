package com.ayoolamasha.fakestore.featureProducts.data.repository

import com.ayoolamasha.fakestore.cache.ProductDaoImpl
import com.ayoolamasha.fakestore.cache.ProductsEntity
import com.ayoolamasha.fakestore.featureProducts.data.mapper.toProductUIData
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductCacheRepositoryImpl @Inject constructor(
    private val productDaoImpl: ProductDaoImpl
){

    suspend fun saveProducts(productsEntity: List<ProductsEntity>) {
        productDaoImpl.insertProduct(productsEntity = productsEntity)
    }

    fun getAllProducts(): List<ProductsUiData> {
        return productDaoImpl.getAllProducts().map { it.toProductUIData() }

    }

    fun getProductById(productId: Int): ProductsUiData {
        return productDaoImpl.getProductById(productId = productId)
            .toProductUIData()
    }
}