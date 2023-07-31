package com.ejemplo.coinswapi.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.coinswapi.data.Repositories.SeccionDtoRepository
import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto
import com.ejemplo.coinswapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeccionesViewModel@Inject constructor(
    private val seccionDtoRepository: SeccionDtoRepository
) : ViewModel() {






        private var seccionId by mutableStateOf(0)

        var nombreSeccion by mutableStateOf("")
        var capacidadSeccion by mutableStateOf(0)
        var precioAsiento by mutableStateOf(0.0)

        private var _state = mutableStateOf(SeccionesState())
        val state: State<SeccionesState> = _state

        init {
            getSecciones()
        }

    fun getSecciones() {
        viewModelScope.launch {
            seccionDtoRepository.getSecciones().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = SeccionesState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = SeccionesState(secciones = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = SeccionesState(error = result.message ?: "Error desconocido")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

        fun putSeccion(id: String, seccionDto: SeccionDto) {
            viewModelScope.launch {
                seccionDtoRepository.putSeccion(id, seccionDto)
            }
        }

    fun getSeccionesByEventoId(eventoId: Int) {
        viewModelScope.launch {
            val secciones = mutableListOf<SeccionDto>()

            seccionDtoRepository.getSeccionesByEventoId(eventoId)
                .collect { secciones.addAll(it) }

            _state.value = state.value.copy(secciones = secciones)
        }
    }



    // Nueva función para obtener una sección por su ID
    fun getSeccionById(seccionId: Int) {
        viewModelScope.launch {
            seccionDtoRepository.getSeccionById(seccionId).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = SeccionesState(isLoading = true)
                    }
                    is Resource.Success -> {
                        result.data?.let { seccion ->
                            // Aquí actualizamos el estado con la sección encontrada
                            _state.value = state.value.copy(secciones = listOf(seccion))
                        } ?: run {
                            // Si la sección no se encuentra, podemos manejar el caso aquí
                            // Por ejemplo, mostrando un mensaje de error o navegando a otra pantalla
                            _state.value = SeccionesState(error = "Sección no encontrada")
                        }
                    }
                    is Resource.Error -> {
                        _state.value = SeccionesState(error = result.message ?: "Error desconocido")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}

