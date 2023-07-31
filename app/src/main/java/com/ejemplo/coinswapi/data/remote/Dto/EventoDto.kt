package com.ejemplo.coinswapi.data.remote.Dto

data class EventoDto (
    val id_evento: Int = 0,
    val nombre_evento: String = "",
    val descripcion_evento: String = "",
    val fecha_evento: String = "",
    val hora_inicio: String = "",
    val hora_finalizacion: String = "",
    val fecha_finalizacion: String = "",
    val capacidad_total: Int = 0
)