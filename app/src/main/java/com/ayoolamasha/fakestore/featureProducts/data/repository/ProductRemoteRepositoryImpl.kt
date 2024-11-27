package com.ayoolamasha.fakestore.featureProducts.data.repository

import com.ayoolamasha.fakestore.apiService.ProductApiServiceImpl
import com.ayoolamasha.fakestore.di.IoDispatcher
import com.ayoolamasha.fakestore.featureProducts.data.mapper.toProductUIData
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import com.ayoolamasha.fakestore.network.extensions.call
import com.ayoolamasha.fakestore.network.mappers.Either
import com.ayoolamasha.fakestore.network.middleware.MiddlewareProvider
import com.ayoolamasha.fakestore.network.model.Failure
import com.ayoolamasha.fakestore.network.model.ResponseMessage
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRemoteRepositoryImpl @Inject constructor(
    private val middlewareProvider: MiddlewareProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseMessage>,
    private val productApiServiceImpl: ProductApiServiceImpl
) {
    suspend fun getProducts(): Either<Failure, List<ProductsUiData>> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                productApiServiceImpl.getProduct()
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess { responseList ->
                responseList?.let { response ->
                    response.map { it.toProductUIData() }

                }
            }

        } as Either<Failure, List<ProductsUiData>>
    }

     suspend fun getProductDetails(productId: Int): Either<Failure, ProductsUiData> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                productApiServiceImpl.getProductDetails(
                    productId = productId
                )
            }
        ).let { response ->
            response.mapSuccess { responseData ->
                responseData
            }.coMapSuccess { responseList ->
                responseList?.let { response ->
                    response.toProductUIData()

                }
            }

        } as Either<Failure, ProductsUiData>
    }
}