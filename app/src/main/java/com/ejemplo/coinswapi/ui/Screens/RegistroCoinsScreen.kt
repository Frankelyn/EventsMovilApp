package com.ejemplo.coinswapi.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PriceCheck
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ejemplo.coinswapi.ViewModels.CoinsViewModel
import org.intellij.lang.annotations.JdkConstants


@Composable
fun Toolbar2() {
    TopAppBar(title = { Text(text = "Registro de Coins") })
}

@Composable
fun RegistroCoinScreen(viewModel: CoinsViewModel = hiltViewModel()){

    var descripcionError by remember { mutableStateOf(false) }
    var valorError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { Toolbar2() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = viewModel.txnombreCoin,
                onValueChange = { viewModel.txnombreCoin = it
                    descripcionError = false},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CurrencyBitcoin,
                        contentDescription = null
                    )
                },
                isError = descripcionError
            )

            validar(error = descripcionError)

            OutlinedTextField(
                value = viewModel.txprecio,
                onValueChange = { viewModel.txprecio = it
                    valorError = false},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PriceCheck,
                        contentDescription = null
                    )
                },
                isError = valorError
            )

            validar(error = valorError)

            OutlinedButton(
                onClick = {
                    descripcionError= viewModel.txnombreCoin.isBlank()
                    valorError=viewModel.txprecio.isBlank()
                    if(!descripcionError && !valorError){
                        if(viewModel.txprecio.toDouble()>0){
                            viewModel.setCoin()
                            //navHostController.navigateUp()
                        }else{
                            Toast.makeText(context, "El precio no puede ser negativo", Toast.LENGTH_LONG).show()
                        }T

                    }
                },
            ) {

            }

        }
    }
}


@Composable
fun validar(error: Boolean){
    val assistiveElementText = if(error) "Error: Obligatorio" else "*obligatorio"
    val assistiveElementColor = if(error) {
        MaterialTheme.colors.error
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    }

    Text(
        text = assistiveElementText,
        color = assistiveElementColor,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
    )
}