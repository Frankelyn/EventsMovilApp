package com.ejemplo.coinswapi.data.remote.Dto

data class CoinDto(
    val MonedaId: Int = 0,
    val Descripcion: String? = "",
    val Valor: Double= 0.0,
    val ImageUrl: String? = ""
)