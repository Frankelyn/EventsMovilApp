package com.ejemplo.coinswapi.data.Repositories

import com.ejemplo.coinswapi.data.remote.API.eventsApi
import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import com.ejemplo.coinswapi.data.remote.Dto.EventoDto
import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto
import com.ejemplo.coinswapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SeccionDtoRepository @Inject constructor(
    private val api: eventsApi
){
    fun getSecciones(): Flow<Resource<List<SeccionDto>>> = flow {
        try {
            emit(Resource.Loading())

            val seccion = api.getSecciones()

            emit(Resource.Success(seccion))
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }


    suspend fun putSeccion(id: String, seccionDto: SeccionDto) {
        try {
            val call = api.putSecciones(id, seccionDto)
            val updatedSeccion = call.body()
            if (call.isSuccessful && updatedSeccion != null) {
                println("La solicitud PUT de secciones se completó exitosamente")
                // Puedes realizar acciones adicionales si es necesario
            } else {
                val errorMessage = "La solicitud PUT de secciones no fue exitosa"
                println(errorMessage)
                // Puedes manejar el error de acuerdo a tus necesidades
            }
        } catch (e: HttpException) {
            val errorMessage = "Error general de HTTP: ${e.message()}"
            println(errorMessage)
            // Puedes manejar el error de acuerdo a tus necesidades
        } catch (e: IOException) {
            val errorMessage = "Error de red: ${e.message}"
            println(errorMessage)
            // Puedes manejar el error de acuerdo a tus necesidades
        }
    }

    fun getSeccionesByEventoId(eventoId: Int): Flow<List<SeccionDto>> {
        return getSecciones()
            .map { resource ->
                when (resource) {
                    is Resource.Success -> resource.data?.filter { it.id_evento == eventoId } ?: emptyList()
                    else -> emptyList()
                }
            }
    }

    fun getSeccionById(seccionId: Int): Flow<Resource<SeccionDto?>> = flow {
        try {
            emit(Resource.Loading())

            val secciones = api.getSecciones()

            val seccionEncontrada = secciones.find { it.id_seccion == seccionId }

            emit(Resource.Success(seccionEncontrada))
        } catch (e: HttpException) {
            // Error general HTTP
            emit(Resource.Error<SeccionDto?>(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            // Verificar la conexión a internet
            emit(Resource.Error<SeccionDto?>("Verificar tu conexión a internet"))
        }
    }


}