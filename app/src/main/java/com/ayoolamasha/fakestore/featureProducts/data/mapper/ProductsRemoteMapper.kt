package com.ayoolamasha.fakestore.featureProducts.data.mapper

import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData

fun GetProducts.toProductUIData(): ProductsUiData{
    return ProductsUiData(
        productId = productId,
        productTitle = productTitle,
        productDescription = productDescription,
        productPrice = productPrice,
        productCategory = productCategory,
        productRating = productRating?.productRate,
        productCount = productRating?.productCount,
        productImage = productImage,
    )
}