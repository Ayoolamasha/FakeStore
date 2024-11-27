package com.ayoolamasha.fakestore.featureProducts.presentation

import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData

data class ProductsUIState(
    val isLoading: Boolean = false,
    val productUIData: List<ProductsUiData>? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val error: String? = ""
)

data class ProductDetailsUIState(
    val isLoading: Boolean = false,
    val productUIData: ProductsUiData? = null,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val error: String? = ""
)