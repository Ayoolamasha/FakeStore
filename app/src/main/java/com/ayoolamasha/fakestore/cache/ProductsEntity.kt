package com.ayoolamasha.fakestore.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductsEntity(
    @PrimaryKey
    val productId: Int?,
    val productTitle: String?,
    val productDescription: String?,
    val productPrice: Double?,
    val productCategory: String?,
    val productRating: Double?,
    val productCount: Int?,
    val productImage: String?,
)