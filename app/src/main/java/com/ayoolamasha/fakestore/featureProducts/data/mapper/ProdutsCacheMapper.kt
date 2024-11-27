package com.ayoolamasha.fakestore.featureProducts.data.mapper

import com.ayoolamasha.fakestore.cache.ProductsEntity
import com.ayoolamasha.fakestore.featureProducts.data.model.GetProducts
import com.ayoolamasha.fakestore.featureProducts.domain.ProductsUiData

fun ProductsEntity.toProductUIData(): ProductsUiData {
    return ProductsUiData(
        productId = productId,
        productTitle = productTitle,
        productDescription = productDescription,
        productPrice = productPrice,
        productCategory = productCategory,
        productRating = productRating,
        productCount = productCount,
        productImage = productImage
    )
}

fun List<ProductsUiData>.toProductListEntity(): List<ProductsEntity>{
    return this.map { toProductEntity(it)}
}

fun toProductEntity(getProducts: ProductsUiData): ProductsEntity {
    return ProductsEntity(
        productId = getProducts.productId,
        productTitle = getProducts.productTitle,
        productDescription = getProducts.productDescription,
        productPrice = getProducts.productPrice,
        productCategory = getProducts.productCategory,
        productRating = getProducts.productRating,
        productCount = getProducts.productCount,
        productImage = getProducts.productImage
    )
}