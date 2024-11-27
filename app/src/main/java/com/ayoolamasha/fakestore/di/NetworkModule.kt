package com.ayoolamasha.fakestore.di

import com.ayoolamasha.fakestore.apiService.ProductApiService
import com.ayoolamasha.fakestore.network.NetworkService
import com.ayoolamasha.fakestore.network.middleware.ConnectivityMiddleware
import com.ayoolamasha.fakestore.network.middleware.MiddlewareProvider
import com.ayoolamasha.fakestore.network.middleware.MiddlewareProviderImpl
import com.ayoolamasha.fakestore.network.model.ResponseMessage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://fakestoreapi.com/"

    @[Provides Singleton]
    fun provideRetrofit(networkFactory: NetworkService): Retrofit =
        networkFactory.createRetrofit(
            url = BASE_URL,
            isDebug = false
        )

    @[Provides Singleton]
    fun provideResponseJsonAdapter(moshi: Moshi): JsonAdapter<ResponseMessage> {
        val typeT = Types.newParameterizedType(ResponseMessage::class.java, String::class.java)
        return moshi.adapter(typeT)
    }


    @[Provides Singleton]
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun bindMiddlewareProvider(
        connectivityMiddlewareImpl:
        ConnectivityMiddleware
    ): MiddlewareProvider =
        MiddlewareProviderImpl.Builder()
            .add(middleware = connectivityMiddlewareImpl)
            .build()


    @Provides
    @Singleton
    fun providesProductsApiService(retrofit: Retrofit): ProductApiService{
        return retrofit.create(ProductApiService::class.java)
    }


}