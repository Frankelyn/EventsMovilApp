package com.ejemplo.coinswapi.data.remote.API

import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface CoinsAPI {
    @GET("/Coins")
    suspend fun getCoins() : List<CoinDto>

    @POST("/Coins")
    suspend fun setCoins(@Body coinDto: CoinDto): Response<CoinDto>
}