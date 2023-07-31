package com.ejemplo.coinswapi.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ejemplo.coinswapi.ViewModels.SeccionesViewModel
import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto
import com.ejemplo.coinswapi.ui.Navigation.ScreenRoutes


@Composable
fun SeccionesScreen(
    navHostController: NavHostController,
    seccionesViewModel: SeccionesViewModel = hiltViewModel(),
) {
    val seccionesState = seccionesViewModel.state.value
    val selectedEventId = EventosScreenData.selectedEventId

    LaunchedEffect(Unit) {
        seccionesViewModel.getSeccionesByEventoId(selectedEventId)
    }

    Column {
        TopAppBar(
            title = { Text(text = "Lista de Secciones") },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(seccionesState.secciones) { seccion ->
                if (seccion.id_evento == selectedEventId) {
                    SeccionItem(seccion = seccion, navHostController)
                }
            }
        }
    }
}

@Composable
fun SeccionItem(seccion: SeccionDto, navHostController: NavHostController) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Nombre: ${seccion.nombre_seccion}", style = MaterialTheme.typography.body1)
            Text(text = "Capacidad: ${seccion.capacidad_seccion}", style = MaterialTheme.typography.body1)

            if (seccion.capacidad_seccion == 0) {
                Text(
                    text = "Sección Llena",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.error // Puedes cambiar el color aquí
                )
            } else {
                var showDialog by remember { mutableStateOf(false) }

                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(text = "Reservar")
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(text = "Reservar sección") },
                        text = { Text(text = "¿Estás seguro de que deseas reservar esta sección?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    // Lógica para reservar la sección
                                    EventosScreenData.selectedSection = seccion
                                    EventosScreenData.selectedSectionId = seccion.id_seccion
                                    navHostController.navigate(ScreenRoutes.reservasScreen.ruta)
                                }
                            ) {
                                Text(text = "Reservar")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false },
                            ) {
                                Text(text = "Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}