package com.ejemplo.coinswapi.data.remote.API

import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import com.ejemplo.coinswapi.data.remote.Dto.EventoDto
import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto
import retrofit2.Response
import retrofit2.http.*

interface eventsApi {
    @GET("/eventos/")
    suspend fun getEventos() : List<EventoDto>

    @GET("/secciones/")
    suspend fun getSecciones(): List<SeccionDto>

    @PUT("/secciones/{id}/")
    suspend fun putSecciones(@Path("id") id: String, @Body seccionDto: SeccionDto): Response<SeccionDto>
}