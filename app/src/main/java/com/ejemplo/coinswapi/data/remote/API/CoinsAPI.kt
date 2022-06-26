package com.ejemplo.coinswapi.data.remote.API

import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import retrofit2.http.GET

interface CoinsAPI {
    @GET("/Coins")
    suspend fun getCoins() : List<CoinDto>
}