package com.ayoolamasha.fakestore.di


import android.content.Context
import androidx.room.Room
import com.ayoolamasha.SHARED_DB_NAME
import com.ayoolamasha.fakestore.cache.ProductDao
import com.ayoolamasha.fakestore.cache.SharedRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideProductsDatabase(@ApplicationContext context: Context): SharedRoomDataBase {
        return Room.databaseBuilder(
            context,
            SharedRoomDataBase::class.java,
            SHARED_DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesProductDao(sharedRoomDataBase: SharedRoomDataBase): ProductDao {
        return sharedRoomDataBase.productDao()
    }

}