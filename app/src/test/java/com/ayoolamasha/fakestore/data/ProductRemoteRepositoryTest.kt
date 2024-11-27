package com.ayoolamasha.fakestore.data

import com.ayoolamasha.fakestore.CustomResponseBody
import com.ayoolamasha.fakestore.apiService.ProductApiService
import com.ayoolamasha.fakestore.apiService.ProductApiServiceImpl
import com.ayoolamasha.fakestore.fakes.FakeData
import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import com.ayoolamasha.fakestore.featureProducts.data.repository.ProductRemoteRepositoryImpl
import com.ayoolamasha.fakestore.network.middleware.MiddlewareProvider
import com.ayoolamasha.fakestore.network.model.ResponseMessage
import com.squareup.moshi.JsonAdapter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductRemoteRepositoryTest {
    private val middlewareProvider: MiddlewareProvider = mockk()
    private val ioDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    private val errorAdapter: JsonAdapter<ResponseMessage> = mockk()
    private val productApiServiceImpl: ProductApiServiceImpl = mockk()
    private lateinit var productRemoteRepositoryImpl: ProductRemoteRepositoryImpl

    @Before
    fun setUp(){
        productRemoteRepositoryImpl = ProductRemoteRepositoryImpl(
            middlewareProvider = middlewareProvider,
            ioDispatcher = ioDispatcher,
            errorAdapter = errorAdapter,
            productApiServiceImpl = productApiServiceImpl
        )

    }

    @Test
    fun `getProducts should return success result`() = runBlocking() {
        val response = Response.success(listOf(FakeData.fakeGetProductsResponse))

        // Mock the dependencies
        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { productApiServiceImpl.getProduct() } returns response

        // Act
        val result = productRemoteRepositoryImpl.getProducts()

        // Assert
        verify { middlewareProvider.getAll() }
        coVerify { productApiServiceImpl.getProduct() }

        // Assert that the result is a success
        assert(result.isSuccess)

    }


    @Test
    fun `getUserRegion should return failure result`() = runBlocking {
        val customResponseBody = CustomResponseBody("Error Message",
            "application/json".toMediaType()
        )

        val response = Response.error<List<GetProducts>>(400, customResponseBody)

        // Mock the dependencies
        coEvery { middlewareProvider.getAll() } returns emptyList()
        coEvery { productApiServiceImpl.getProduct() } returns response

        // Act
        val result = productRemoteRepositoryImpl.getProducts()

        // Assert
        verify { middlewareProvider.getAll() }
        coVerify { productApiServiceImpl.getProduct() }

        // Assert that the result is a failure
        assert(result.isError)
        // You can add further checks on the failure type or message
    }
}