package com.ejemplo.coinswapi.ViewModels


import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto

data class SeccionesState (
    val isLoading: Boolean = false,
    val secciones: List<SeccionDto> = emptyList(),
    val error: String = ""
)