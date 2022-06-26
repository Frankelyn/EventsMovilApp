package com.ejemplo.coinswapi.data.Repositories

import com.ejemplo.coinswapi.data.remote.API.CoinsAPI
import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import com.ejemplo.coinswapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinsDtoRepository @Inject constructor(
    private val api: CoinsAPI
) {
    fun getListExchanges(): Flow<Resource<List<CoinDto>>> = flow {
        try {
            emit(Resource.Loading())

            val exchanges = api.getCoins()

            emit(Resource.Success(exchanges))
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}