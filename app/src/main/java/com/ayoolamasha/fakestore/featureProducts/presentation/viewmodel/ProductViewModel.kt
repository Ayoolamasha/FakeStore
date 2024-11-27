package com.ayoolamasha.fakestore.featureProducts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoolamasha.fakestore.featureProducts.domain.MakeGetProductsCallsUseCase
import com.ayoolamasha.fakestore.featureProducts.domain.MakeProductDetailsCallsUseCase
import com.ayoolamasha.fakestore.network.mappers.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.util.concurrent.CancellationException
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val makeGetProductsCallsUseCase: MakeGetProductsCallsUseCase,
    private val makeProductDetailsCallsUseCase: MakeProductDetailsCallsUseCase
): ViewModel(){

    private val _getProductState = MutableStateFlow(ProductsUIState())
    val getProductState get() = _getProductState

    private val _getGetProductDetailsState = MutableStateFlow(ProductDetailsUIState())
    val getGetProductDetailsState get() = _getGetProductDetailsState


    fun makeGetProductsCall() {
        makeGetProductsCallsUseCase().onEach { response ->
            try {
                when (response) {
                    is NetworkResult.Loading -> {
                        _getProductState.update {
                            ProductsUIState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success -> {
                        _getProductState.update {
                            ProductsUIState(productUIData = response.data, isSuccess = true)
                        }
                    }

                    is NetworkResult.Error -> {
                        _getProductState.update {
                            ProductsUIState(error = response.message, isError = true)
                        }
                    }

                    else -> {}
                }

            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    _getProductState.update {
                        ProductsUIState(error = response.message, isError = true)
                    }
                } else {
                    throw t
                }

            }
        }.launchIn(viewModelScope)
    }


    fun makeGetProductDetailsCall(productId: Int) {
        makeProductDetailsCallsUseCase(params = productId).onEach { response ->
            try {
                when (response) {
                    is NetworkResult.Loading -> {
                        _getGetProductDetailsState.update {
                            ProductDetailsUIState(isLoading = true)
                        }
                    }

                    is NetworkResult.Success -> {
                        _getGetProductDetailsState.update {
                            ProductDetailsUIState(productUIData = response.data, isSuccess = true)
                        }
                    }

                    is NetworkResult.Error -> {
                        _getGetProductDetailsState.update {
                            ProductDetailsUIState(error = response.message, isError = true)
                        }
                    }

                    else -> {}
                }

            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    _getGetProductDetailsState.update {
                        ProductDetailsUIState(error = response.message, isError = true)
                    }
                } else {
                    throw t
                }

            }
        }.launchIn(viewModelScope)
    }
}