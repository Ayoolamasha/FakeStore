package com.ayoolamasha.fakestore.featureProducts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductSharedViewModel @Inject constructor(): ViewModel(){
    var productsUiData: ProductsUiData? = null
}