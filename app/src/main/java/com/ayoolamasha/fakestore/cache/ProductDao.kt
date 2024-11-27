package com.ayoolamasha.fakestore.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProducts(productsEntity: List<ProductsEntity>)

    @Query("SELECT * FROM ProductsEntity")
    fun getAllProducts(): List<ProductsEntity>

    @Query("SELECT * FROM ProductsEntity WHERE productId = :productId")
    fun getProductById(productId: Int): ProductsEntity
}