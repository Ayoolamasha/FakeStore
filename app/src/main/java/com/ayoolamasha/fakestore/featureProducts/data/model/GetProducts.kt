package com.ayoolamasha.fakestore.featureProducts.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetProducts(
    @Json(name = "id")
    val productId: Int?,
    @Json(name = "title")
    val productTitle: String?,
    @Json(name = "description")
    val productDescription: String?,
    @Json(name = "price")
    val productPrice: Double?,
    @Json(name = "category")
    val productCategory: String?,
    @Json(name = "image")
    val productImage: String?,
    @Json(name = "rating")
    val productRating: ProductRatings?,

)

@JsonClass(generateAdapter = true)
data class ProductRatings(
    @Json(name = "rate")
    val productRate: Double?,
    @Json(name = "count")
    val productCount: Int?,
)
