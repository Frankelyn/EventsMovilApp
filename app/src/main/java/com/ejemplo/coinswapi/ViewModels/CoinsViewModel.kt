package com.ejemplo.coinswapi.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.coinswapi.data.Repositories.CoinsDtoRepository
import com.ejemplo.coinswapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinsDtoRepository: CoinsDtoRepository
) : ViewModel() {

    private var _state = mutableStateOf(CoinsState())
    val state: State<CoinsState> = _state

    init {
        coinsDtoRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinsState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinsState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinsState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
}