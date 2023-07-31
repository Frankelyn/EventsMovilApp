package com.ejemplo.coinswapi.data.remote.Dto

import android.os.Parcelable
import com.squareup.moshi.Json
import java.math.BigDecimal

data class SeccionDto (
    val id_seccion: Int = 0,
    val id_evento: Int = 0,
    val nombre_seccion: String = "",
    val capacidad_seccion: Int = 0,
    @Json(name = "precio_asiento") val precio_asiento: String = ""
)