package com.ayoolamasha.fakestore.domain

import com.ayoolamasha.fakestore.fakes.FakeData
import com.ayoolamasha.fakestore.featureProducts.domain.MakeGetProductsCallsUseCase
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import com.ayoolamasha.fakestore.featureProducts.domain.repository.ProductDomainRepositoryImpl
import com.ayoolamasha.fakestore.network.mappers.NetworkResult
import io.mockk.mockk
import io.mockk.coEvery
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class MakeGetProductCallUseCaseTest {
    private val domainProductRepository = mockk<ProductDomainRepositoryImpl>()
    private val ioDispatcher = UnconfinedTestDispatcher()
    private val useCase = MakeGetProductsCallsUseCase(domainProductRepository, ioDispatcher)

    @Test
    fun `execute should return success result`() = runBlocking {
        val products = FakeData.fakeProductsUiData
        coEvery { domainProductRepository.getProducts() } returns flowOf(
            NetworkResult.Success(listOf(products)))


        val result = useCase.execute()

        // Assert
        val resultList = mutableListOf<NetworkResult<List<ProductsUiData>>>()
        result.collect { resultList.add(it) }

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Success)
    }

    @Test
    fun `execute should return error result`() = runBlocking {

        coEvery { domainProductRepository.getProducts() } returns flowOf(NetworkResult.Error("Test error"))


        val result = useCase.execute()

        // Assert
        val resultList = mutableListOf<NetworkResult<List<ProductsUiData>>>()
        result.collect { resultList.add(it) }

        assert(resultList.size == 1)
        assert(resultList[0] is NetworkResult.Error)
    }
}