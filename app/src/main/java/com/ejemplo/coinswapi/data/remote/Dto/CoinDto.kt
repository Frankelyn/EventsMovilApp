package com.ejemplo.coinswapi.data.remote.Dto

data class CoinDto(
    val monedaId: Int = 0,
    val descripcion: String = "",
    val valor: Double= 0.0,
    val imageUrl: String = ""
)