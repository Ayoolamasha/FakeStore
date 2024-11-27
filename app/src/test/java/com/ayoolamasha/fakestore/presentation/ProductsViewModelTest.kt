package com.ayoolamasha.fakestore.presentation

import com.ayoolamasha.fakestore.fakes.FakeData
import com.ayoolamasha.fakestore.featureProducts.domain.MakeGetProductsCallsUseCase
import com.ayoolamasha.fakestore.featureProducts.domain.MakeProductDetailsCallsUseCase
import com.ayoolamasha.fakestore.featureProducts.presentation.viewmodel.ProductViewModel
import com.ayoolamasha.fakestore.network.mappers.NetworkResult
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest {
    private lateinit var productsViewModelTest: ProductViewModel
    private val makeGetProductsCallsUseCase: MakeGetProductsCallsUseCase = mockk()
    private  val makeProductDetailsCallsUseCase: MakeProductDetailsCallsUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        productsViewModelTest = ProductViewModel(makeGetProductsCallsUseCase,makeProductDetailsCallsUseCase)
    }

    @Test
    fun makeGetProductCallSuccess() = runTest {
        val mockProductCall = FakeData.fakeProductsUiData

        coEvery { makeGetProductsCallsUseCase() } returns flow {
            emit(NetworkResult.Loading())
            emit(NetworkResult.Success(listOf(mockProductCall)))
        }

        productsViewModelTest.makeGetProductsCall()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = productsViewModelTest.getProductState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isSuccess).isTrue()
        assertThat(state.productUIData).isEqualTo(listOf(mockProductCall))
    }


    @Test
    fun testMakeProductDetailsCall_Error() = runTest {
        val errorMessage = "Please Check Your Internet"

        coEvery { makeGetProductsCallsUseCase() } returns flow {
            emit(NetworkResult.Loading())
            emit(NetworkResult.Error(errorMessage))
        }

        productsViewModelTest.makeGetProductsCall()
        testDispatcher.scheduler.advanceUntilIdle()

        val state = productsViewModelTest.getProductState.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isError).isTrue()
        assertThat(state.error).isEqualTo(errorMessage)
    }

}