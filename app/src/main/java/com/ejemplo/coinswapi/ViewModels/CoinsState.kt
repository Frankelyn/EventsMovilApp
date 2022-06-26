package com.ejemplo.coinswapi.ViewModels

import com.ejemplo.coinswapi.data.remote.Dto.CoinDto

data class CoinsState(
    val isLoading: Boolean = false,
    val coins: List<CoinDto> = emptyList(),
    val error: String = ""
)