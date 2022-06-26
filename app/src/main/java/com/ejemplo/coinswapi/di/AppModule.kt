package com.ejemplo.coinswapi.di

import com.ejemplo.coinswapi.data.Repositories.CoinsDtoRepository
import com.ejemplo.coinswapi.data.remote.API.CoinsAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinApi(moshi: Moshi): CoinsAPI {
        return Retrofit.Builder()
            .baseUrl("http://www.yamato07.somee.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CoinsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinsApi: CoinsAPI): CoinsDtoRepository {
        return CoinsDtoRepository(coinsApi)
    }
}