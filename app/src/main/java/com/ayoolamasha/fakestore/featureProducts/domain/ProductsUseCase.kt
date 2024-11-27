package com.ayoolamasha.fakestore.featureProducts.domain

import com.ayoolamasha.fakestore.di.IoDispatcher
import com.ayoolamasha.fakestore.featureProducts.domain.repository.ProductDomainRepositoryImpl
import com.ayoolamasha.fakestore.network.BaseUseCase
import com.ayoolamasha.fakestore.network.mappers.NetworkResult
import com.ayoolamasha.fakestore.network.requireParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeGetProductsCallsUseCase @Inject constructor(
    private val productDomainRepositoryImpl: ProductDomainRepositoryImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseUseCase<Any, NetworkResult<List<ProductsUiData>>>() {
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: Any?): Flow<NetworkResult<List<ProductsUiData>>> {
        return productDomainRepositoryImpl.getProducts()
    }
}

@Singleton
class MakeProductDetailsCallsUseCase @Inject constructor(
    private val productDomainRepositoryImpl: ProductDomainRepositoryImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseUseCase<Int, NetworkResult<ProductsUiData>>() {
    override val dispatcher: CoroutineDispatcher
        get() = ioDispatcher

    override fun execute(params: Int?): Flow<NetworkResult<ProductsUiData>> {
        requireParams(params)
        return productDomainRepositoryImpl.getProductDetails(productId = params)
    }
}

