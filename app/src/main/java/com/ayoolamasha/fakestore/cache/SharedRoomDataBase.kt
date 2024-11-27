package com.ayoolamasha.fakestore.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductsEntity::class], exportSchema = true, version = 1)
abstract class SharedRoomDataBase : RoomDatabase(){
    abstract fun productDao(): ProductDao
}