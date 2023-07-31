package com.ejemplo.coinswapi.ui.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ejemplo.coinswapi.ViewModels.EventosViewModel
import com.ejemplo.coinswapi.data.remote.Dto.EventoDto
import com.ejemplo.coinswapi.ui.Navigation.ScreenRoutes

@Composable
fun EventosScreen(
    navHostController: NavHostController,
    viewModel: EventosViewModel = hiltViewModel(),
) {
    val selectedEventId = remember { mutableStateOf(-1) }

    @Composable
    fun Toolbar() {
        TopAppBar() {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Lista de Eventos",
                    style = MaterialTheme.typography.h5,
                    fontStyle = FontStyle.Italic
                )

            }
        }
    }

    val state = viewModel.state.value

    Scaffold(
        topBar = { Toolbar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.eventos) { evento ->
                    EventoItem(evento = evento) {
                        selectedEventId.value = evento.id_evento
                        EventosScreenData.selectedEventId = evento.id_evento
                        navHostController.navigate(ScreenRoutes.listaSeccionesScreen.ruta)
                    }
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()
        }
    }
}

@Composable
fun EventoItem(
    evento: EventoDto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = evento.nombre_evento)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = evento.descripcion_evento)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = evento.fecha_evento)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Hora: ${evento.hora_inicio} - ${evento.hora_finalizacion}")
            }
        }
    }
}
