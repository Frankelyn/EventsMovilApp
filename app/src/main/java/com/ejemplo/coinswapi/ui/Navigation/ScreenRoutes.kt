package com.ejemplo.coinswapi.ui.Navigation

sealed class ScreenRoutes (val ruta: String){
    object ConsultaCoinsScreen: ScreenRoutes("ConsultaCoins")
    object PostCoinScreen: ScreenRoutes("RegistroCoins")
    object ListaEventosScreen: ScreenRoutes("ListaEventos")
    object listaSeccionesScreen: ScreenRoutes("ListaSecciones")
    object reservasScreen: ScreenRoutes("reservasScreen")
}