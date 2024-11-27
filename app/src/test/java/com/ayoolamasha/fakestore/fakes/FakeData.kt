package com.ayoolamasha.fakestore.fakes

import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData

object FakeData {

    val fakeProductsUiData = ProductsUiData(
        productId = 1,
        productTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        productDescription = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve," +
                " your everyday",
        productPrice = 109.95,
        productCategory = "men's clothing",
        productRating = 3.9,
        productCount = 120,
        productImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",

    )

    val fakeGetProductsResponse = GetProducts(
        productId = 1,
        productTitle = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        productDescription = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve," +
                " your everyday",
        productPrice = 109.95,
        productCategory = "men's clothing",
        productRating = null,
        productImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",

        )
}