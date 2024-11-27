package com.ayoolamasha.fakestore.featureProducts.domain.repository

import com.ayoolamasha.fakestore.cache.ProductDaoImpl
import com.ayoolamasha.fakestore.featureProducts.data.mapper.toProductListEntity
import com.ayoolamasha.fakestore.featureProducts.data.repository.ProductCacheRepositoryImpl
import com.ayoolamasha.fakestore.featureProducts.data.repository.ProductRemoteRepositoryImpl
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import com.ayoolamasha.fakestore.network.mappers.NetworkResult
import com.ayoolamasha.fakestore.network.model.getErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDomainRepositoryImpl @Inject constructor(
    private val productRemoteRepositoryImpl: ProductRemoteRepositoryImpl,
    private val productCacheRepositoryImpl: ProductCacheRepositoryImpl
){
    fun getProducts(): Flow<NetworkResult<List<ProductsUiData>>>{
        return flow {
            emit(NetworkResult.Loading())
            val response = productRemoteRepositoryImpl.getProducts()

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))
                    productCacheRepositoryImpl.saveProducts(productsEntity = responseUI.toProductListEntity())

                }
            } else {
                val cachedProduct =
                    productCacheRepositoryImpl.getAllProducts()
                if (cachedProduct.isEmpty().not()) {
                    emit(NetworkResult.Success(data = cachedProduct))
                } else {
                    val failure = response.getFailureOrNull()
                    if (failure != null) {
                        emit(NetworkResult.Error(message = failure.getErrorMessage()))
                    }
                }
            }
        }
        }


    fun getProductDetails(productId: Int): Flow<NetworkResult<ProductsUiData>>{
        return flow {
            emit(NetworkResult.Loading())
            val response = productRemoteRepositoryImpl.getProductDetails(productId = productId)

            if (response.isSuccess) {
                val responseUI = response.getSuccessOrNull()
                if (responseUI != null) {
                    emit(NetworkResult.Success(data = responseUI))

                }
            } else {
                val cachedProduct = productCacheRepositoryImpl.getProductById(productId = productId)
                if (cachedProduct.productId != null) {
                    emit(NetworkResult.Success(data = cachedProduct))
                } else {
                    val failure = response.getFailureOrNull()
                    if (failure != null) {
                        emit(NetworkResult.Error(message = failure.getErrorMessage()))
                    }
                }
            }
        }
    }

}