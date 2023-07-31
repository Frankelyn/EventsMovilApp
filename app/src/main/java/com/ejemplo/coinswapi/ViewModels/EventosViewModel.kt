package com.ejemplo.coinswapi.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.coinswapi.data.Repositories.EventoDtoRepository
import com.ejemplo.coinswapi.data.remote.Dto.EventoDto
import com.ejemplo.coinswapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventosViewModel @Inject constructor(
    private val eventosDtoRepository: EventoDtoRepository
) : ViewModel() {

    var nombreEvento by mutableStateOf("")
    var descripcionEvento by mutableStateOf("")
    var fechaEvento by mutableStateOf("")
    var horaInicio by mutableStateOf("")
    var horaFinalizacion by mutableStateOf("")
    var fechaFinalizacion by mutableStateOf("")
    var capacidadTotal by mutableStateOf(0)

    private var _state = mutableStateOf(EventosState())
    val state: State<EventosState> = _state

    init {
        eventosDtoRepository.getEventos().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = EventosState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = EventosState(eventos = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = EventosState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }

}
