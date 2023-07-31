package com.ejemplo.coinswapi.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ejemplo.coinswapi.ViewModels.SeccionesViewModel
import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto

@Composable
fun ReservasScreen(
    navHostController: NavHostController,
    seccionesViewModel: SeccionesViewModel = hiltViewModel()
) {
    val seccion: SeccionDto = EventosScreenData.selectedSection ?: return // Return if selectedSection is null

    var cantidadReservada by remember { mutableStateOf("") }
    var mostrarExito by remember { mutableStateOf(false) }
    var montoTotal by remember { mutableStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Reservas") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Text(text = "Sección: ${seccion.nombre_seccion}", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capacidad: ${seccion.capacidad_seccion}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = cantidadReservada,
                onValueChange = { cantidad ->
                    cantidadReservada = cantidad
                },
                label = { Text("Cantidad de asientos a reservar") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = { /* Handle keyboard done */ }),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Precio por asiento: ${seccion.precio_asiento}", style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val cantidad = cantidadReservada.toIntOrNull()
                    if (cantidad != null && cantidad > 0 && cantidad <= seccion.capacidad_seccion) {
                        val precioAsiento = seccion.precio_asiento.toFloatOrNull() ?: 0f
                        montoTotal = cantidad * precioAsiento // Calculate the monto total based on the current value
                        mostrarExito = true // Show the AlertDialog
                    } else {
                        // Mostrar mensaje de error
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            ) {
                Text(text = "Reservar")
            }
        }
    }

    if (mostrarExito) {
        AlertDialog(
            onDismissRequest = {
                mostrarExito = false // Close the AlertDialog when dismissing it
            },
            title = { Text("Confirmar Reserva") },
            text = {
                Column {
                    Text("Sección: ${seccion.nombre_seccion}")
                    Text("Cantidad de asientos a reservar: $cantidadReservada")
                    Text("Monto total a pagar: $montoTotal")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Hacemos el put directamente al confirmar la reserva
                        val cantidad = cantidadReservada.toIntOrNull()
                        if (cantidad != null && cantidad > 0 && cantidad <= seccion.capacidad_seccion) {
                            seccionesViewModel.putSeccion(
                                seccion.id_seccion.toString(),
                                seccion.copy(capacidad_seccion = seccion.capacidad_seccion - cantidad)
                            )
                            navHostController.navigateUp() // Navigate back to the previous screen when confirming the reservation
                        } else {
                            // Mostrar mensaje de error
                        }
                        mostrarExito = false // Close the AlertDialog after confirming the reservation
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        mostrarExito = false // Just dismiss the AlertDialog when canceling
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
