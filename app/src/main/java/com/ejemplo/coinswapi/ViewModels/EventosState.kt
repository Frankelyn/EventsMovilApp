package com.ejemplo.coinswapi.ViewModels

import com.ejemplo.coinswapi.data.remote.Dto.EventoDto

data class EventosState (
    val isLoading: Boolean = false,
    val eventos: List<EventoDto> = emptyList(),
    val error: String = ""
)