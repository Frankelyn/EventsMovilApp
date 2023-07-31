package com.ejemplo.coinswapi.data.Repositories

import com.ejemplo.coinswapi.data.remote.API.eventsApi
import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import com.ejemplo.coinswapi.data.remote.Dto.EventoDto
import com.ejemplo.coinswapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventoDtoRepository @Inject constructor(
    private  val api: eventsApi
)  {
    fun getEventos(): Flow<Resource<List<EventoDto>>> = flow {
        try {
            emit(Resource.Loading())

            val evento = api.getEventos()

            emit(Resource.Success(evento))
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}