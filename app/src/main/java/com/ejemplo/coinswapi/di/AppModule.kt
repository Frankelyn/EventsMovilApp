package com.ejemplo.coinswapi.di

import com.ejemplo.coinswapi.data.Repositories.CoinsDtoRepository
import com.ejemplo.coinswapi.data.Repositories.EventoDtoRepository
import com.ejemplo.coinswapi.data.Repositories.SeccionDtoRepository
import com.ejemplo.coinswapi.data.remote.API.CoinsAPI
import com.ejemplo.coinswapi.data.remote.API.eventsApi
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigDecimal
import javax.inject.Singleton

class BigDecimalAdapter {
    @FromJson
    @BigDecimalJson
    fun fromJson(value: String): BigDecimal {
        return BigDecimal(value)
    }

    @ToJson
    fun toJson(@BigDecimalJson value: BigDecimal): String {
        return value.toString()
    }
}

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class BigDecimalJson

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(BigDecimalAdapter()) // Agregar el adaptador para BigDecimal
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
    fun provideEventsApi(moshi: Moshi): eventsApi {
        return Retrofit.Builder()
            .baseUrl("https://5b40-190-94-102-188.ngrok.io")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(eventsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsRepository(eventApi: eventsApi): EventoDtoRepository {
        return EventoDtoRepository(eventApi)
    }

    @Provides
    @Singleton
    fun provideSectionRepository(eventApi: eventsApi): SeccionDtoRepository {
        return SeccionDtoRepository(eventApi)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(coinsApi: CoinsAPI): CoinsDtoRepository {
        return CoinsDtoRepository(coinsApi)
    }
}
